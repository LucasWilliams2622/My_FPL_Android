package com.example.myfplapplication.Utilities;

import java.util.HashMap;

public class Constants {
    public static final String KEY_COLLECTION_USERS = "users";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PREFERENCE_NAME = "chatAppPreference";
    public static final String KEY_IS_SIGNED_IN = "isSignedID";
    public static final String KEY_USER_ID = "userId";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_FCM_TOKEN = "fcmToken";
    public static final String KEY_USER = "user";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_IS_VERIFY = "isVerify";


    public static final String KEY_COLLECTION_CHAT = "chat";
    public static final String KEY_SENDER_ID = "senderId";
    public static final String KEY_RECEIVER_ID = "receiverId";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TIMESTAMP = "timestamp";

    public static final String KEY_COLLECTION_CONVERSATIONS = "conversation";
    public static final String KEY_SENDER_NAME = "senderName";
    public static final String KEY_RECEIVER_NAME = "receiverName";
    public static final String KEY_SENDER_IMAGE = "senderImage";
    public static final String KEY_RECEIVER_IMAGE = "receiverImage";
    public static final String KEY_LAST_MESSAGE = "lastMessage";

    public static final String KEY_AVAILABILITY = "availability";
    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS   = "registration_ids";


    public static HashMap<String, String> remoteMsgHeader = null;

    public static HashMap<String, String> getRemoteMsgHeader() {
        if (remoteMsgHeader == null) {
            remoteMsgHeader = new HashMap<>();
            remoteMsgHeader.put(
                    REMOTE_MSG_AUTHORIZATION,
                    "key=AAAATr51wEQ:APA91bH5Z3TaCnpk8plB3ZmvDuXKFj9XWQnNj5KOsNQLdZVjneshIO8vj3t8hACbhHOklChpzRLZl-uAEnM8jxAWRFUwYYJHYhanhk23HU0f8VsLIShKZ1ESdjaJ4gbtRIk7-4uXgcqC"
            );
            remoteMsgHeader.put(
                    REMOTE_MSG_CONTENT_TYPE,
                    "application/json"
            );

        }
        return remoteMsgHeader;
    }


}
