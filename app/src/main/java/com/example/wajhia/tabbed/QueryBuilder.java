package com.example.wajhia.tabbed;

import com.example.wajhia.tabbed.Numbers;

/**
 * Created by wajhia on 5/8/2016.
 */
public class QueryBuilder { /**
 * Specify your database name here
 *
 * @return
 */
public String getDatabaseName() {
    return "code101";
}

        /**
         * Specify your MongoLab API here
         *
         * @return
         */
        public String getApiKey() {
            return "xBIEs9sKcjfeg93_9gUaQgKve-64Y38Y";
        }

        /**
         * This constructs the URL that allows you to manage your database,
         * collections and documents
         *
         * @return
         */
        public String getBaseUrl() {
            return "https://api.mongolab.com/api/1/databases/" + getDatabaseName() + "/collections/";
        }

        /**
         * Completes the formating of your URL and adds your API key at the end
         *
         * @return
         */
        public String docApiKeyUrl() {
            return "?apiKey=" + getApiKey();
        }

        /**
         * Returns the docs101 collection
         *
         * @return
         */
        public String documentRequest() {
            return "project-users";
        }

        /**
         * Builds a complete URL using the methods specified above
         *
         * @return
         */
        public String buildContactsSaveURL() {
            return getBaseUrl() + documentRequest() + docApiKeyUrl();
        }

        /**
         * Formats the contact details for MongoHQ Posting
         *
         * @param number: Details of the person
         * @return
         */
        public String createContact(Numbers number) {
            return String
                    .format("{\"document\"  : {\"Email\": \"%s\", "
                                    + "\"Password\": \"%s\", " + "\"Phone\": \"%s\"," + "\"House\": \"%s\"}, \"safe\" : true}",
                            number.Email, number.Password, number.Phone, number.House);
        }

        public String setContactData(Numbers number) {
            return String.format("{ \"$set\" : "
                            + "{\"Email\" : \"%s\", "
                            + "\"Password\" : \"%s\", "
                            + "\"Phone\" : \"%s\", "
                            + "\"House\" : \"%s\" "

                            + " }" + "}",
                    number.getemail(),
                    number.getpass(),
                    number.getphone(),
                    number.gethouse());

        }
}
