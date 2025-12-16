package com.moneymate.moneymate.ui.finance.component

import com.moneymate.moneymate.R

//전역으로 선언. 언론사 리스트
object PublisherProvider {
    val publisherList = listOf(
        PublisherData(
            "HK",
            "한국경제",
            "세상을 보는 균형",
            R.drawable.img_news_hk,
            listOf(
                CategoryData("economy", true),
                CategoryData("stock", false),
                CategoryData("realestate", false)
            )
        ),
        PublisherData(
            "MK",
            "매일경제",
            "Make Knowledge",
            R.drawable.img_news_mk,
            listOf(
                CategoryData("economy", true),
                CategoryData("stock", false),
                CategoryData("realestate", false),
                CategoryData("business", false)
            )
        ),
        PublisherData(
            "FNN",
            "파이낸셜뉴스",
            "first-class 경제신문",
            R.drawable.img_news_fnn,
            listOf(
                CategoryData("economy", true),
                CategoryData("stock", false),
                CategoryData("finance", false),
                CategoryData("realestate", false)
            )
        ),
        PublisherData(
            "YN",
            "연합뉴스",
            "내 손 안의 뉴스",
            R.drawable.img_news_yn,
            listOf(CategoryData("economy", true))
        ),
        PublisherData(
            "CN",
            "조선일보",
            "Facts First:언제나 당신 손에",
            R.drawable.img_news_cn,
            listOf(CategoryData("economy", true))
        ),
        PublisherData(
            "HG",
            "한겨레",
            "세상을 보는 정직한 눈",
            R.drawable.img_news_hg,
            listOf(CategoryData("economy", true))
        ),
        PublisherData(
            "YF",
            "Yahoo Finance",
            "Your #1 Finance Destination",
            R.drawable.img_news_yf,
            listOf(CategoryData("economy", true)) //영문 표기 되도록 빈칸 넘겨줄거임
        ),
        PublisherData(
            "FT",
            "Financial Times",
            "We live in Financial Times",
            R.drawable.img_news_ft,
            listOf(
                CategoryData("economy", true),
            ),
        )
    )

    //enum - 언론사 이름 매칭에 용이하도록
    val publisherMap: Map<String, PublisherData> = publisherList.associateBy { it.enum }
}