package com.moneymate.moneymate.util

import androidx.annotation.DrawableRes
import com.moneymate.moneymate.R

/**
 * 은행 코드를 해당하는 drawable 리소스로 매핑하는 유틸리티 클래스
 */
object BankIconMapper {
    
    /**
     * 은행 코드에 해당하는 drawable 리소스 ID를 반환합니다.
     * 매칭되는 은행이 없을 경우 기본 이미지를 반환합니다.
     * 
     * @param bankCode 은행 코드 (예: "004", "011", "088" 등)
     * @return drawable 리소스 ID
     */
    @DrawableRes
    fun getBankIcon(bankCode: String): Int {
        return when (bankCode) {
            "001" -> R.drawable.ic_moneymatelogo // 한국은행
            "002" -> R.drawable.ic_bank_kdb // 산업은행
            "003" -> R.drawable.ic_bank_ibk // 기업은행
            "004" -> R.drawable.ic_bank_kb // 국민은행
            "005" -> R.drawable.ic_moneymatelogo // 외환은행
            "007" -> R.drawable.ic_bank_suhyeop // 수협은행
            "008" -> R.drawable.ic_moneymatelogo // 수출입은행
            "011" -> R.drawable.ic_bank_nonghyeop // 농협은행
            "012" -> R.drawable.ic_bank_nonghyeop // 농협회원조합
            "020" -> R.drawable.ic_bank_woori // 우리은행
            "023" -> R.drawable.ic_bank_sc // SC제일은행
            "026" -> R.drawable.ic_moneymatelogo // 서울은행
            "027" -> R.drawable.ic_bank_citi // 한국씨티은행
            "031" -> R.drawable.ic_bank_daegu // iM뱅크(대구)
            "032" -> R.drawable.ic_bank_busan // 부산은행
            "034" -> R.drawable.ic_bank_gwangju // 광주은행
            "035" -> R.drawable.ic_bank_shinhan// 제주은행
            "037" -> R.drawable.ic_bank_gwangju// 전북은행
            "039" -> R.drawable.ic_bank_busan// 경남은행
            "045" -> R.drawable.ic_bank_saemaeul // 새마을금고연합회
            "048" -> R.drawable.ic_bank_shinhyeop // 신협중앙회
            "050" -> R.drawable.ic_bank_mutualsaving// 상호저축은행
            "051" -> R.drawable.ic_moneymatelogo // 기타 외국계은행
            "052" -> R.drawable.ic_moneymatelogo // 모건스탠리은행
            "054" -> R.drawable.ic_bank_hsbc // HSBC은행
            "055" -> R.drawable.ic_bank_deutsche // 도이치은행
            "056" -> R.drawable.ic_moneymatelogo // 알비에스피엘씨은행
            "057" -> R.drawable.ic_bank_jpmorgan // 제이피모간체이스은행
            "058" -> R.drawable.ic_moneymatelogo // 미즈호코퍼레이트은행
            "059" -> R.drawable.ic_moneymatelogo // 미쓰비시도쿄UFJ은행
            "060" -> R.drawable.ic_moneymatelogo // BOA
            "061" -> R.drawable.ic_moneymatelogo // 비엔피파리바은행
            "062" -> R.drawable.ic_bank_icbc// 중국공상은행
            "063" -> R.drawable.ic_bank_china// 중국은행
            "064" -> R.drawable.ic_bank_sj// 산림조합
            "065" -> R.drawable.ic_moneymatelogo // 대화은행
            "071" -> R.drawable.ic_bank_post // 우체국
            "076" -> R.drawable.ic_moneymatelogo // 신용보증기금
            "077" -> R.drawable.ic_moneymatelogo // 기술신용보증기금
            "081" -> R.drawable.ic_bank_hana // 하나은행
            "088" -> R.drawable.ic_bank_shinhan // 신한은행
            "089" -> R.drawable.ic_bank_kbank // 케이뱅크
            "090" -> R.drawable.ic_bank_kakao // 카카오뱅크
            "092" -> R.drawable.ic_bank_toss // 토스뱅크
            "093" -> R.drawable.ic_moneymatelogo // 한국주택금융공사
            "094" -> R.drawable.ic_moneymatelogo // 서울보증보험
            "095" -> R.drawable.ic_moneymatelogo // 경찰청
            "099" -> R.drawable.ic_moneymatelogo // 금융결제원
            "209" -> R.drawable.ic_bank_yuanta // 동양종합금융증권
            "218" -> R.drawable.ic_moneymatelogo // 현대증권
            "230" -> R.drawable.ic_bank_miraeasset // 미래에셋증권
            "238" -> R.drawable.ic_bank_miraeasset// 대우증권
            "240" -> R.drawable.ic_bank_samsung // 삼성증권
            "243" -> R.drawable.ic_bank_koraninvest // 한국투자증권
            "247" -> R.drawable.ic_bank_nonghyeop// NH투자증권
            "261" -> R.drawable.ic_bank_kyobo // 교보증권
            "262" -> R.drawable.ic_bank_daegu// 하이투자증권
            "263" -> R.drawable.ic_bank_hmc// 에이치엠씨투자증권
            "264" -> R.drawable.ic_bank_kium // 키움증권
            "265" -> R.drawable.ic_bank_ebest// 이트레이드증권
            "266" -> R.drawable.ic_bank_sk// SK증권
            "267" -> R.drawable.ic_bank_daesin // 대신증권
            "268" -> R.drawable.ic_moneymatelogo // 솔로몬투자증권
            "269" -> R.drawable.ic_bank_hanwha // 한화증권
            "270" -> R.drawable.ic_bank_hana// 하나대투증권
            "271" -> R.drawable.ic_bank_toss // 토스증권
            "278" -> R.drawable.ic_bank_shinhan // 신한금융투자
            "279" -> R.drawable.ic_bank_db// 동부증권
            "280" -> R.drawable.ic_bank_eugene// 유진투자증권
            "287" -> R.drawable.ic_bank_meritz // 메리츠증권
            "289" -> R.drawable.ic_bank_nonghyeop// 엔에이치투자증권
            "290" -> R.drawable.ic_bank_bookook // 부국증권
            "291" -> R.drawable.ic_bank_shinyoung// 신영증권
            "292" -> R.drawable.ic_moneymatelogo // 엘아이지투자증권
            else -> R.drawable.ic_moneymatelogo// 기본 이미지
        }
    }
}

