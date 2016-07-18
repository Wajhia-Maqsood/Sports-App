package com.example.wajhia.tabbed;
public class BuildQuery_match {

    /**
     * Specify your database name here
     * @return
     */


    public String getDatabaseName() {
        return "sportsgala";
    }

    /**
     * Specify your MongoLab API here
     * @return
     */
    public String getApiKey() {
        return "O1STCg9mTD8jWUODUKuk-pVaM3nIt16v";
    }

    /**
     * This constructs the URL that allows you to manage your database,
     * collections and documents
     * @return
     */
    public String getBaseUrl()
    {
        return "https://api.mongolab.com/api/1/databases/"+getDatabaseName()+"/collections/";
    }

    /**
     * Completes the formating of your URL and adds your API key at the end
     * @return
     */
    public String docApiKeyUrl()
    {
        return "?apiKey="+getApiKey();
    }

    /**
     * Returns the docs101 collection
     * @return
     */
    public String documentRequest()
    {
        return "matches" ;
    }
    public String collection_Name()
    {
        return "ScoreCard" ;
    }


    /**
     * Builds a complete URL using the methods specified above
     * @return
     */
    public String buildURL()
    {
        return getBaseUrl()+documentRequest()+docApiKeyUrl();
    }
    public String buildanotherURL()
    {
        return getBaseUrl()+collection_Name()+docApiKeyUrl();
    }

    /**
     * Formats the contact details for MongoHQ Posting
     * @param match: Details of the person
     * @return
     */




}
