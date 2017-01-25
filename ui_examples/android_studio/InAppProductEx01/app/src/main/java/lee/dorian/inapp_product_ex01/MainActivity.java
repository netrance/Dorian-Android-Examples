package lee.dorian.inapp_product_ex01;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lee.dorian.inapp_product_ex01.data.InAppProductInfo;
import lee.dorian.inapp_product_ex01.inapp_pay_util.IabHelper;
import lee.dorian.inapp_product_ex01.inapp_pay_util.IabResult;
import lee.dorian.inapp_product_ex01.inapp_pay_util.Inventory;
import lee.dorian.inapp_product_ex01.inapp_pay_util.Purchase;
import lee.dorian.inapp_product_ex01.inapp_pay_util.SkuDetails;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "InAppProductEx01";

    // Views.
    private TextView tvSampleItemInfo;
    private Button btnPurchaseSampleItem;
    private Button btnConsumeSampleItem;

    /** In-app billing helper */
    private IabHelper mIabHelper;

    /** Information about the in-app items of this application */
    private Inventory mInventory;

    /** The details of a sample item */
    private SkuDetails mSampleItemDetails;

    /** The public key IabHelper object needs */
    private String mBase64EncodedPublicKey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes IabHelper object.
        mBase64EncodedPublicKey = getResources().getString(R.string.base64_encoded_public_key);
        mIabHelper = new IabHelper(this, mBase64EncodedPublicKey);
        mIabHelper.startSetup(iabSetupFinishedListener);

        // Gets views.
        tvSampleItemInfo = (TextView)findViewById(R.id.tvSampleItemInfo);
        btnPurchaseSampleItem = (Button)findViewById(R.id.btnPurchaseSampleItem);
        btnConsumeSampleItem = (Button)findViewById(R.id.btnConsumeSampleItem);

        // Sets listeners of views.
        btnPurchaseSampleItem.setOnClickListener(btnPurchaseItemClickListener);
        btnConsumeSampleItem.setOnClickListener(btnConsumeSampleItemClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (mIabHelper == null) return;

        // Pass on the activity result to the helper for handling
        if (!mIabHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        }
        else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }

    private View.OnClickListener btnPurchaseItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null != mInventory.getPurchase(InAppProductInfo.SKU_SAMPLE_PRODUCT)) {
                openSimpleDialog(
                        "Notice",
                        "Already purchased the sample item. You can buy it again after consuming it."
                );
                return;
            }

            try {
                mIabHelper.launchPurchaseFlow(
                        MainActivity.this,
                        InAppProductInfo.SKU_SAMPLE_PRODUCT,
                        InAppProductInfo.REQ_CODE_BUY_SAMPLE_PRODUCT,
                        purchaseFinishedListener,
                        "test inapp payment"
                );
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener btnConsumeSampleItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null == mInventory.getPurchase(InAppProductInfo.SKU_SAMPLE_PRODUCT)) {
                openSimpleDialog("Warning", "No purchased the sample item.");
                return;
            }

            try {
                mIabHelper.consumeAsync(
                        mInventory.getPurchase(InAppProductInfo.SKU_SAMPLE_PRODUCT),
                        consumeFinishedListener
                );
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
                openSimpleDialog("Error", "Cannot consume the sample item.");
            }
        }
    };

    private IabHelper.OnIabSetupFinishedListener iabSetupFinishedListener = new IabHelper.OnIabSetupFinishedListener() {
        @Override
        public void onIabSetupFinished(IabResult result) {
            if (!result.isSuccess()) {
                Log.d(TAG, "Problem setting up In-app Billing: " + result);
                openSimpleDialog("Error", "Cannot set up in-app billing.");
                return;
            }

            try {
                mIabHelper.queryInventoryAsync(queryInventoryFinishedListener);
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
                openSimpleDialog("Error", "Cannot read purchase info. (IabAsyncInProgressException)");
            }
        }
    };

    private IabHelper.QueryInventoryFinishedListener queryInventoryFinishedListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (result.isFailure()) {
                openSimpleDialog("Error", "Cannot read purchase info.");
                return;
            }

            mInventory = inventory;
            mSampleItemDetails = mInventory.getSkuDetails(InAppProductInfo.SKU_SAMPLE_PRODUCT);
            updateSampleItemInfo();
        }
    };

    private IabHelper.OnIabPurchaseFinishedListener purchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                openSimpleDialog("Error", "Purchasement cancelled or cannot buy the item.");
                return;
            }
            else if (purchase.getSku().equals(InAppProductInfo.SKU_SAMPLE_PRODUCT)) {
                openSimpleDialog("Notice", "Thank you for purchasing the sample product.");

                try {
                    mIabHelper.queryInventoryAsync(queryInventoryFinishedListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private IabHelper.OnConsumeFinishedListener consumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isFailure()) {
                openSimpleDialog("Error", "Cannot consume the sample item.");
                return;
            }

            openSimpleDialog("Notice", "The sample item was consumed.");
            try {
                mIabHelper.queryInventoryAsync(queryInventoryFinishedListener);
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            if (null != mIabHelper) {
                mIabHelper.dispose();
                mIabHelper = null;
            }
        } catch (IabHelper.IabAsyncInProgressException e) {
            e.printStackTrace();
        }
    }

    private void openSimpleDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which)-> dialog.dismiss())
                .show();
    }

    private void updateSampleItemInfo() {
        tvSampleItemInfo.setText(
                "Purchased sample item: "
                        + mInventory.hasPurchase(InAppProductInfo.SKU_SAMPLE_PRODUCT)
        );
    }
}
