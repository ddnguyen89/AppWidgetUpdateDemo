package nguyen.appwidgetupdatedemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    DateFormat df = new SimpleDateFormat("hh:mm:ss");

    /*
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = NewAppWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }*/

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int i = 0; i < appWidgetIds.length; i++) {
            //updateAppWidte(context, appWidgetManager, appWidgetIds[i]
            int currentWidgetId = appWidgetIds[i];

            Intent intent = new Intent(Intent.ACTION_VIEW);     //displays the widget
           // intent.setData(Uri.parse("sms: "));     //sends an intent to open specific URI

            //pending intent is a token you give a foreign application
            //which allows the foreign application to use your application's permission
            //to execute a predefiend peace of code
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);      //launch the provided pending intent
            views.setTextViewText(R.id.appwidget_text, df.format(new Date()));


            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(currentWidgetId, views);
        }
    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

