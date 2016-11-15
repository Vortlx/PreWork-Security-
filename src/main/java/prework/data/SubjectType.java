package prework.data;

public enum SubjectType {
    LECTURE{
        @Override
        public String toString() {
            return "Lecture";
        }
    },
    PRACTICE{
        @Override
        public String toString() {
            return "Practice";
        }
    };
}