package com.onlineCourse.eduhub.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtil {

    public static String timeAgo(Instant time) {
        if (time == null) return "N/A";

        Duration d = Duration.between(time, Instant.now());

        if (d.toMinutes() < 1) return "just now";
        if (d.toHours() < 1) return d.toMinutes() + " minutes ago";
        if (d.toDays() < 1) return d.toHours() + " hours ago";
        return d.toDays() + " days ago";
    }

    public static String timeAgo(LocalDateTime time) {
        if (time == null) return "N/A";
        return timeAgo(time.atZone(ZoneId.systemDefault()).toInstant());
    }
}