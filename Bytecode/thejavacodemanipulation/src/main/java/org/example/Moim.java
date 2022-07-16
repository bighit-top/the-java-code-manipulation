package org.example;

public class Moim {
    int maxNumberOfAttendees;
    int numberOfEnrollment;

    /**
     * 코드 커버리지는 어떻게 측정하나
     */
    public boolean isEnrollmentFull() {
        if (maxNumberOfAttendees == 0) {
            return false;
        }
        if (numberOfEnrollment < maxNumberOfAttendees) {
            return false;
        }
        return true;
    }
}
