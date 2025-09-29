package com.devdam.server.util;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

public class BaseUtils {
    private static String BASE_URL;
    public static final int LIST_PAGINATE_AMOUNT = 15;
    public static final int TOKEN_EXPIRE_AFTER_X_MINUTES = 15;
    public static final String DEFAULT_PROFILE_PHOTO_URL = "https://res.cloudinary.com/drivedex-cloud/image/upload/v1702872901/web/avatars/default-profile-photo.png";
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    public static final String[] APP_SETTING_NAMES = {"coming soon", "under maintenance", "website ready"};

    //public static final String[] HIGH_LEVEL_USER_ROLES = {"SUPERUSER", "MANAGER"};
    public static final String[] HIGH_LEVEL_USER_ROLES = {"ADMIN", "ADMINISTRATOR", "SUPERUSER", "MANAGER", "SUPERVISOR"};
    public static final String[] SUPER_USER_ROLES = {"ADMIN", "ADMINISTRATOR", "SUPERUSER"};

    public static final String GOOGLE_FORM_FEEDBACK_LINK = "https://forms.gle/2fR968ffKeHn3aTA6";


    // Sleeps the program for x seconds
    public static void sleep(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException ignore) {}
    }

    // Calculate 'time ago' whether it be seconds, minutes, hours, etc.
    public static String timeStamp(Date timestamp){
        // Convert the Date object to an Instant
        Instant eventTime = timestamp.toInstant();

        // Get the current time
        Instant currentTime = Instant.now();

        // Calculate the duration between the current time and the event time
        Duration duration = Duration.between(eventTime, currentTime);

        // Get the seconds, minutes, and hours from the duration
        // Calculate the period between the current date and the event date
        LocalDate eventDate = eventTime.atZone(java.time.ZoneOffset.UTC).toLocalDate();
        LocalDate currentDate = currentTime.atZone(java.time.ZoneOffset.UTC).toLocalDate();
        Period period = Period.between(eventDate, currentDate);

        // Determine the appropriate time unit
        String timeUnit;
        long timeValue;

        if (duration.toMinutes() < 1) {
            timeUnit = duration.getSeconds() > 1 ? "seconds" : "second";
            timeValue = duration.getSeconds();
        }
        else if (duration.toHours() < 1) {
            timeUnit = duration.toMinutes() > 1 ? "minutes" : "minute";
            timeValue = duration.toMinutes();
        }
        else if (duration.toDays() < 1) {
            timeUnit = duration.toHours() > 1 ? "hours" : "hour";
            timeValue = duration.toHours();
        }
        else if (period.getYears() > 0) {
            timeUnit = period.getYears() > 1 ? "years" : "year";
            timeValue = period.getYears();
        }
        else if (period.getMonths() > 0) {
            timeUnit = period.getMonths() > 1 ? "months" : "month";
            timeValue = period.getMonths();
        }
        else if (period.getDays() >= 7) {
            timeUnit = (period.getDays() / 7) > 1 ? "weeks": "week";
            timeValue = period.getDays() / 7;
        } 
        else {
            timeUnit = period.getDays() > 1 ? "days" : "day";
            timeValue = period.getDays();
        }

        return "Location last updated " + timeValue + " " + timeUnit + " ago.";
    }

    // Convert date object to the format of 'December 31, 2023 at 2:30 p.m.'
    public static String lastLoggedInString(Date date){
        // Create a LocalDateTime object representing the date and time
        // Convert Date to LocalDateTime
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Define a custom date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a");

        // Format the LocalDateTime object using the formatter
        return localDateTime.format(formatter);
    }

    // Convert date object to the format of 'December 31, 2023 at 2:30 p.m.'
    public static String currentDateInString(){
        Date date = new Date();

        // Create a LocalDateTime object representing the date and time
        // Convert Date to LocalDateTime
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Define a custom date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");

        // Format the LocalDateTime object using the formatter
        return localDateTime.format(formatter);
    }

    public static String currentYearInString(){
        Date date = new Date();

        // Create a LocalDateTime object representing the date and time
        // Convert Date to LocalDateTime
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // Define a custom date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");

        // Format the LocalDateTime object using the formatter
        return localDateTime.format(formatter);
    }

    // Convert string to lower case
    public static String lowerCase(String s) {
        return s.toLowerCase();
    }

    // // Convert string to title case
    // public static String titleCase(String s, boolean delimiterChecker) {
    //     if(delimiterChecker) return WordUtils.capitalizeFully(s, ' ', '-', '_');

    //     return WordUtils.capitalizeFully(s);
    // }

    // Generate Random Password
    // Source: https://stackoverflow.com/a/31260788/15978450
    public static String generateRandomPassword(int count){
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();

        return RandomStringUtils.random(count, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom());
    }

    // Generate Random Username
    public static String generateRandomUsername(){
        Faker faker = new Faker();

        return faker.name().username();
    }

    // Generate random token
    public static String generateToken(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "$-+*.";
        String numbers = "0123456789";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;

        Random random = new Random();
        StringBuilder token = new StringBuilder();

        for(int i = 0; i < length; i++) {
            token.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
        }

        return token.toString();
    }

    // Generate random otp [one time password]
    public static String generateOTP(int length) {
        String numbers = "0123456789";

        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for(int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }

        return otp.toString();
    }

    // Add minutes to current time
    public static Date addMinutesToTime(int minutes){
        // Source: https://www.javaprogramto.com/2020/04/java-add-minutes-to-date.html

        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.MINUTE, minutes);

        return currentTimeNow.getTime();
    }

    // Add hours to current time
    public static Date addHoursToTime(int hours){
        // Source: https://www.javaprogramto.com/2020/04/java-add-minutes-to-date.html

        Calendar currentTimeNow = Calendar.getInstance();
        currentTimeNow.add(Calendar.HOUR, hours);

        return currentTimeNow.getTime();
    }

    // Check if a give date is expired
    public static boolean isDateExpired(Date expDate){
        LocalDateTime now = LocalDateTime.now();

        Instant instant = Instant.ofEpochMilli(expDate.getTime());
        LocalDateTime expiring = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return now.isAfter(expiring);
    }

    // Get the base url for the website that will be used in the email templates, etc.
    public static String getBaseURL(){
        if(BASE_URL == null) BASE_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        // In dev mode -> http://localhost:8080
        return BASE_URL;
    }

    // Create slug from text
    public static String toSlug(String text) {
        // Source: https://stackoverflow.com/a/1657250/15978450

        String nowhitespace = WHITESPACE.matcher(text).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");

        return slug.toLowerCase(Locale.ENGLISH);
    }

    // Create slug from text with a random number attached
    public static String buildSlug(String text){
        Random random = new Random();
        String slug = toSlug(text) + "-" + random.nextInt(99, 99999);

        return slug;
    }

    // This method will be used to convert fare price to the string format
    public static String formatDecimal(Double value){
        // Format with two decimal places using DecimalFormat
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        return "$" + decimalFormat.format(value) + " JMD";
    }

    // This method will be used to convert counters to the string format
    public static String formatNumber(Integer value){
        // Format with two decimal places using DecimalFormat
        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        return decimalFormat.format(value);
    }
}
