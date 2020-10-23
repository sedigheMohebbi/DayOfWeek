import java.util.Scanner;

public class DayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Date");
        String inputDate = scanner.nextLine();
        String[] fromDate = inputDate.split("/");
        String fromMonthStr = fromDate[0];
        String fromDayStr = fromDate[1];
        if (validate(fromMonthStr, fromDayStr)) {
            System.out.println("Enter day of week");
            String dayOfWeek = scanner.nextLine();
            if (getDayOfWeekFromValue(dayOfWeek) == null) {
                System.out.println("Invalid Day of week.");
                return;
            }
            Days inputDay = getDayOfWeekFromValue(dayOfWeek);
            System.out.println("enter to date");
            String toDateStr = scanner.nextLine();
            String[] toDate = toDateStr.split("/");
            String toMonthStr = toDate[0];
            String toDayStr = toDate[1];
            if (validateFromAndFrom(toMonthStr, fromMonthStr)) {
                if (validate(toMonthStr, toDayStr)) {
                    Days day = calculate(fromMonthStr, fromDayStr, toMonthStr, toDayStr, inputDay.getValue());
                    System.out.println(day);
                } else {
                    System.out.println("invalid input");
                }
            } else System.out.println("To date should be after From Date");
        }
    }

    private static boolean validateFromAndFrom(String toMonthStr, String fromMonthStr) {
        return (Integer.parseInt(toMonthStr) >= Integer.parseInt(fromMonthStr));
    }

    private static Days calculate(String fromMonthStr, String fromDayStr, String toMonthStr, String toDayStr, int inputDay) {
        int fromMonth = Integer.parseInt(fromMonthStr);
        int fromDay = Integer.parseInt(fromDayStr);
        int toMonth = Integer.parseInt(toMonthStr);
        int toDay = Integer.parseInt(toDayStr);
        int daysOfMonth = getDaysOfMonth(fromMonth);
        int result;
        if (fromMonth == toMonth) {
            result = toDay - fromDay;
        } else {
            result = (daysOfMonth - fromDay) + getMonthBetween(fromMonth, toMonth) + toDay;
        }
        System.out.println("Result" + result);
        int diffInsideWeek = result % 7;
        System.out.println("result%7" + diffInsideWeek);
        System.out.println("input day" + inputDay);
        return calculateDayOfWeek(diffInsideWeek, inputDay);

    }

    private static Days calculateDayOfWeek(int a, int fromDate) {
        int b = a + fromDate;
        System.out.println("b " + b);
        return getDayFromResult(b % 7);
    }

    private static Days getDayFromResult(int i) {
        return getDayOfWeekFromValue(String.valueOf(i));
    }

    private static int getMonthBetween(int fromMonth, int toMonth) {
        int result = 0;
        for (int i = fromMonth + 1; i < toMonth; i++) {
            result += getDaysOfMonth(i);
        }
        return result;
    }

    private static int getDaysOfMonth(int month) {
        if (month <= 6) {
            return 31;
        }
        if (month <= 11) {
            return 30;
        }
        return 29;
    }

    private static Days getDayOfWeekFromValue(String dayOfWeek) {
        switch (dayOfWeek.toLowerCase()) {
            case "0":
            case "sat":
            case "saturday":
                return Days.saturday;
            case "1":
            case "sun":
            case "sunday":
                return Days.sunday;
            case "2":
            case "mon":
            case "monday":
                return Days.monday;
            case "3":
            case "tue":
            case "tuesday":
                return Days.tuesday;
            case "4":
            case "wen":
            case "wednesday":
                return Days.wednesday;
            case "5":
            case "thu":
            case "thursday":
                return Days.thursday;
            case "6":
            case "fri":
            case "friday":
                return Days.friday;
            default:
                return null;
        }
    }


    private static boolean validate(String monthStr, String dayStr) {
        if (!monthStr.matches("\\d+") && !dayStr.matches("\\d+")) {
            return false;
        }
        int month = Integer.parseInt(monthStr);
        int day = Integer.parseInt(dayStr);
        if (month > 12 || month <= 0) {
            return false;
        } else if (day <= 0 || day > 31) {
            return false;
        } else if (month < 12 && month > 6) {
            if (day > 30) {
                return false;
            }
        } else if (month == 12) {
            if (day > 29) {
                return false;
            }
        }
        return true;

    }
}
