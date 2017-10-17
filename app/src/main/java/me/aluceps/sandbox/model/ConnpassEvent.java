package me.aluceps.sandbox.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConnpassEvent {

    @SerializedName("results_returned")
    private int resultsReturned;

    private List<Event> events;

    @SerializedName("results_start")
    private int resultsStart;

    @SerializedName("results_available")
    private int resultsAvailable;

    public static class Event {

        @SerializedName("event_url")
        private String eventUrl;

        @SerializedName("event_type")
        private String eventType;

        @SerializedName("owner_nickname")
        private String ownerNickname;

        private Series series;

        @SerializedName("updated_at")
        private String updatedAt;

        private String lat;

        @SerializedName("started_at")
        private String startedAt;

        @SerializedName("hash_tag")
        private String hashTag;

        private String title;

        @SerializedName("event_id")
        private int eventId;

        private String lon;

        private int waiting;

        private int limit;

        @SerializedName("owner_id")
        private int ownerId;

        @SerializedName("owner_display_name")
        private String ownerDisplayName;

        private String description;

        private String address;

        @SerializedName("catch")
        private String catchcopy;

        private int accepted;

        @SerializedName("ended_at")
        private String endedAt;

        private String place;

        static class Series {

            private String url;

            private int id;

            private String title;

            public String getUrl() {
                return url;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }

        public String getEventUrl() {
            return eventUrl;
        }

        public String getEventType() {
            return eventType;
        }

        public String getOwnerNickname() {
            return ownerNickname;
        }

        public Series getSeries() {
            return series;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public String getLat() {
            return lat;
        }

        public String getStartedAt() {
            return startedAt;
        }

        public String getHashTag() {
            return hashTag;
        }

        public String getTitle() {
            return title;
        }

        public int getEventId() {
            return eventId;
        }

        public String getLon() {
            return lon;
        }

        public int getWaiting() {
            return waiting;
        }

        public int getLimit() {
            return limit;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public String getOwnerDisplayName() {
            return ownerDisplayName;
        }

        public String getDescription() {
            return description;
        }

        public String getAddress() {
            return address;
        }

        public String getCatchcopy() {
            return catchcopy;
        }

        public int getAccepted() {
            return accepted;
        }

        public String getEndedAt() {
            return endedAt;
        }

        public String getPlace() {
            return place;
        }
    }

    public int getResultsReturned() {
        return resultsReturned;
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getResultsStart() {
        return resultsStart;
    }

    public int getResultsAvailable() {
        return resultsAvailable;
    }
}
