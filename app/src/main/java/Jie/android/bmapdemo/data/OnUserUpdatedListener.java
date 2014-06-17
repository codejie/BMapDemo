package jie.android.bmapdemo.data;

/**
 * Created by Administrator on 2014/6/17.
 */
public interface OnUserUpdatedListener {
    public void onSelfDataUpdated(final UserData data);
    public void onBuddyDataAdded(final UserData data);
    public void onBuddyDataRemoved(final UserData data);
    public void onBuddyDataUpdated(final UserData data);
    public void onBuddyDataRefreshed();
}
