package com.good.solscript.ui


import android.annotation.SuppressLint
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.good.solscript.R
import com.good.solscript.adapter.UsageListAdapter
import com.good.solscript.util.CustomUsageStats
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class SubscriptManageFragment : Fragment() {

    private val TAG = SubscriptManageFragment::class.java.simpleName

    //VisibleForTesting for variables below
    private lateinit var mUsageStatsManager: UsageStatsManager
    private lateinit var mUsageListAdapter: UsageListAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mOpenUsageSettingButton: Button
    private lateinit var mSpinner: Spinner

    fun newInstance(): SubscriptManageFragment {
        return SubscriptManageFragment()
    }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mUsageStatsManager = activity
            ?.getSystemService("usagestats") as UsageStatsManager //Context.USAGE_STATS_SERVICE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subscript_manage, container, false)
    }

    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)

        mUsageListAdapter = UsageListAdapter()
        mRecyclerView = rootView.findViewById<View>(R.id.recyclerview_app_usage) as RecyclerView
        mLayoutManager = mRecyclerView.layoutManager!!
        mRecyclerView.scrollToPosition(0)
        mRecyclerView.adapter = mUsageListAdapter
        mOpenUsageSettingButton =
            rootView.findViewById<View>(R.id.button_open_usage_setting) as Button
        mSpinner = rootView.findViewById<View>(R.id.spinner_time_span) as Spinner
        val spinnerAdapter = ArrayAdapter.createFromResource(
            this.context!!,
            R.array.action_list, android.R.layout.simple_spinner_dropdown_item
        )
        mSpinner.adapter = spinnerAdapter
        mSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            var strings = resources.getStringArray(R.array.action_list)

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val statsUsageInterval = StatsUsageInterval
                    .getValue(strings[position])
                if (statsUsageInterval != null) {
                    val usageStatsList = getUsageStatistics(statsUsageInterval.mInterval)
                    Collections.sort(usageStatsList, LeastTimeLaunchedComparatorAsc())
                    updateAppsList(usageStatsList)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    /**
     * Returns the [.mRecyclerView] including the time span specified by the
     * intervalType argument.
     *
     * @param intervalType The time interval by which the stats are aggregated.
     * Corresponding to the value of [UsageStatsManager].
     * E.g. [UsageStatsManager.INTERVAL_DAILY], [                     ][UsageStatsManager.INTERVAL_WEEKLY],
     *
     * @return A list of [android.app.usage.UsageStats].
     */
    fun getUsageStatistics(intervalType: Int): List<UsageStats> {
        // Get the app statistics since one year ago from the current time.
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -1)

        val queryUsageStats = mUsageStatsManager
            .queryUsageStats(
                intervalType, cal.timeInMillis,
                System.currentTimeMillis()
            )

        if (queryUsageStats.size == 0) {
            Log.i(TAG, "The user may not allow the access to apps usage. ")
            Toast.makeText(
                activity,
                getString(R.string.explanation_access_to_appusage_is_not_enabled),
                Toast.LENGTH_LONG
            ).show()
            mOpenUsageSettingButton.visibility = View.VISIBLE
            mOpenUsageSettingButton.setOnClickListener {
                startActivity(
                    Intent(
                        Settings.ACTION_USAGE_ACCESS_SETTINGS
                    )
                )
            }
        }
        return queryUsageStats
    }

    /**
     * Updates the [.mRecyclerView] with the list of [UsageStats] passed as an argument.
     *
     * @param usageStatsList A list of [UsageStats] from which update the
     * [.mRecyclerView].
     */
    //VisibleForTesting
    internal fun updateAppsList(usageStatsList: List<UsageStats>) {
        val customUsageStatsList = ArrayList<CustomUsageStats>()
        for (i in usageStatsList.indices) {
            val customUsageStats = CustomUsageStats()
            customUsageStats.usageStats = usageStatsList[i]
            try {
                val appIcon = activity?.packageManager!!
                    .getApplicationIcon(customUsageStats.usageStats.packageName)
                customUsageStats.appIcon = appIcon
            } catch (e: PackageManager.NameNotFoundException) {
                Log.w(
                    TAG, String.format(
                        "App Icon is not found for %s",
                        customUsageStats.usageStats.packageName
                    )
                )
                customUsageStats.appIcon = activity
                    ?.getDrawable(R.drawable.ic_launcher_foreground)
            }

            customUsageStatsList.add(customUsageStats)
        }
        mUsageListAdapter.setCustomUsageStatsList(customUsageStatsList)
        mUsageListAdapter.notifyDataSetChanged()
        mRecyclerView.scrollToPosition(0)
    }

    /**
     * The [Comparator] to sort a collection of [UsageStats] sorted by the timestamp
     * last time the app was used in the descendant order.
     */
    private class LastTimeLaunchedComparatorDesc : Comparator<UsageStats> {

        override fun compare(left: UsageStats, right: UsageStats): Int {
            return left.lastTimeUsed.compareTo(right.lastTimeUsed)
        }
    }

    private class LeastTimeLaunchedComparatorAsc : Comparator<UsageStats> {

        override fun compare(left: UsageStats, right: UsageStats): Int {
            Log.d("compare  ",""+right.totalTimeInForeground.compareTo(left.totalTimeInForeground))
            return right.totalTimeInForeground.compareTo(left.totalTimeInForeground)
        }
    }

    /**
     * Enum represents the intervals for [android.app.usage.UsageStatsManager] so that
     * values for intervals can be found by a String representation.
     *
     */
    //VisibleForTesting
    internal enum class StatsUsageInterval constructor(
        val mStringRepresentation: String,
        val mInterval: Int
    ) {
        DAILY("Daily", UsageStatsManager.INTERVAL_DAILY),
        WEEKLY("Weekly", UsageStatsManager.INTERVAL_WEEKLY),
        MONTHLY("Monthly", UsageStatsManager.INTERVAL_MONTHLY),
        YEARLY("Yearly", UsageStatsManager.INTERVAL_YEARLY);


        companion object {

            fun getValue(stringRepresentation: String): StatsUsageInterval? {
                for (statsUsageInterval in values()) {
                    if (statsUsageInterval.mStringRepresentation == stringRepresentation) {
                        return statsUsageInterval
                    }
                }
                return null
            }
        }
    }
}
