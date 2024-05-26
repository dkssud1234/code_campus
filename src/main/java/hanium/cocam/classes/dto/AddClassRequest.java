//package hanium.cocam.classes.dto;
//
//import hanium.cocam.classes.ClassType;
//import hanium.cocam.classes.Classes;
//import hanium.cocam.user.User;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class AddClassRequest {
//    private String classTitle;
//    private String classLevel;
//    private ClassType classType;
//    private String classArea;
//    private String classDate;
//    private String classIntro;
//    private String subjectName;
//    private String chatLink;
//
//    public Classes toEntity(User user) {
//        return Classes.builder()
//                .classTitle(classTitle)
//                .classLevel(classLevel)
//                .classType(classType)
//                .classArea(classArea)
//                .classDate(classDate)
//                .classIntro(classIntro)
//                .subjectName(subjectName)
//                .chatLink(chatLink)
//                .userNo(user)
//                .build();
//    }
//}
