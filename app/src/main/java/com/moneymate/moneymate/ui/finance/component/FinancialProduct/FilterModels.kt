package com.moneymate.moneymate.ui.finance.component.FinancialProduct

enum class Period(val code: Int) { M1(1), M3(3), M6(6), M12(12), M24(24), M36(36) }
enum class FinGrpCode(val code: String) { ALL("all"), BANK("bank"), SAVINGS("savingsBank") }
enum class IntrType(val code: String) { ALL("all"), SIMPLE("S"), COMPOUND("M") }
enum class JoinDeny(val code: String) { NONE("1"), LOW_INCOME("2"), PARTIAL("3") }

// 지역은 다중 선택 → 서버 문자열과 같게 맞춤
enum class Region(val code: String) {
    ALL("all"), SEOUL("seoul"), BUSAN("busan"), DAEGU("daegu"), INCHEON("incheon"),
    GWANGJU("gwangju"), DAEJEON("daejeon"), ULSAN("ulsan"), SEJONG("sejong"),
    GYEONGGI("gyeonggi"), GANGWON("gangwon"), CHUNGBUK("chungbuk"), CHUNGNAM("chungnam"),
    JEONBUK("jeonbuk"), JEONNAM("jeonnam"), GYEONGBUK("gyeongbuk"), GYEONGNAM("gyeongnam"),
    JEJU("jeju")
}
enum class JoinWay(val code: String) {
    ALL("all"), BRANCH("branch"), INTERNET("internet"), SMARTPHONE("smartphone"),
    RECRUITER("recruiter"), TELEPHONE("telephone"), OTHERS("others")
}