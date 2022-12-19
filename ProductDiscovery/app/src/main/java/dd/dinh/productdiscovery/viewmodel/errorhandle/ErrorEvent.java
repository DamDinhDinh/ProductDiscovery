package dd.dinh.productdiscovery.viewmodel.errorhandle;

import androidx.annotation.StringRes;

public interface ErrorEvent {

    @StringRes
    int getErrorMessage();
}
