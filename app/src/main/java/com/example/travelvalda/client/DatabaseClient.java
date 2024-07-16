package com.example.travelvalda.client;

import android.content.Context;
import androidx.room.Room;

import com.example.travelvalda.database.AppDatabase;
import com.example.travelvalda.models.Property;

public class DatabaseClient {
    private Context mContext;
    private static DatabaseClient mInstance;

    // Our app database object
    private AppDatabase appDatabase;

    private DatabaseClient(Context mContext) {
        this.mContext = mContext;

        // Creating the app database with Room database builder
        // TravelValdaDB is the name of the database
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "TravelValdaDB")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new DatabaseClient(mContext);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public void addInitialData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Kiểm tra xem đã có dữ liệu chưa
                if (appDatabase.propertyDao().getAllProperties().isEmpty()) {
                    // Thêm dữ liệu cứng cho Property
                    Property property1 = new Property();
                    property1.setOwnerId(1);
                    property1.setTitle("Sunshine Hotel");
                    property1.setDescription("A nice place to stay in the city.");
                    property1.setPricePerNight(100.0);
                    property1.setLocation("Downtown, City");
                    property1.setImageUrl("https://thuthuatnhanh.com/wp-content/uploads/2020/01/hinh-anh-mau-biet-thu-2-tang-dep.jpg"); // Đường dẫn ảnh

                    Property property2 = new Property();
                    property2.setOwnerId(2);
                    property2.setTitle("City Central Hotel");
                    property2.setDescription("Close to all major attractions.");
                    property2.setPricePerNight(150.0);
                    property2.setLocation("City Center, Downtown");
                    property2.setImageUrl("https://thuthuatnhanh.com/wp-content/uploads/2020/01/hinh-anh-mau-biet-thu-3-tang-dep-sang-trong-va-hien-dai-scaled.jpg"); // Đường dẫn ảnh

                    appDatabase.propertyDao().insertAll(property1, property2);
                }
            }
        }).start();
    }

}
