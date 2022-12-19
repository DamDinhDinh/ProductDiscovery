package dd.dinh.productdiscovery.viewmodel.errorhandle;

import dd.dinh.productdiscovery.R;

public enum ProductDiscoverErrorEvent implements ErrorEvent{
    NONE(0),
    NO_INTERNET(R.string.error_msg_no_internet),
    EMPTY(R.string.error_msg_empty_database);

    private int mResourceId;

    ProductDiscoverErrorEvent(int resourceId) {
        this.mResourceId = resourceId;
    }

    @Override
    public int getErrorMessage() {
        return mResourceId;
    }
}
