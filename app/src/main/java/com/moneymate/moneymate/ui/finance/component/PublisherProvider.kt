package com.moneymate.moneymate.ui.finance.component

//전역으로 선언. 언론사 리스트
object PublisherProvider {
    val publisherList = listOf(
        PublisherData(
            "HK",
            "한국경제",
            "세상을 보는 균형",
            " ",
            listOf("economy", "stock", "realestate")
        ),
        PublisherData(
            "MK",
            "매일경제",
            "Make Knowledge",
            "",
            listOf("economy", "stock", "realestate", "business")
        ),
        PublisherData(
            "FNN",
            "파이낸셜뉴스",
            "first-class 경제신문",
            "",
            listOf("economy", "stock", "finance", "realestate")
        ),
        PublisherData(
            "YN",
            "연합뉴스",
            "내 손 안의 뉴스",
            "",
            listOf("economy")
        ),
        PublisherData(
            "CN",
            "조선일보",
            "Facts First:언제나 당신 손에",
            "",
            listOf("economy")
        ),
        PublisherData(
            "HG",
            "한겨레",
            "세상을 보는 정직한 눈",
            "",
            listOf("economy")
        ),
        PublisherData(
            "YF",
            "Yahoo Finance",
            "Your #1 Finance Destination",
            "",
            listOf(" ") //영문 표기 되도록 빈칸 넘겨줄거임
        ),
        PublisherData(
            "FT",
            "Financial Times",
            "We live in Financial Times",
            "",
            listOf(" ")
        ),
    )

    //enum - 언론사 이름 매칭에 용이하도록
    val publisherMap: Map<String, PublisherData> = publisherList.associateBy { it.enum }
}