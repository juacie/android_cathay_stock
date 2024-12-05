package com.juacie.cathay.stock.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.juacie.cathay.stock.R

val roboto = FontFamily(
    Font(resId = R.font.roboto_regular),
    Font(resId = R.font.roboto_black, weight = FontWeight.Black),
    Font(resId = R.font.roboto_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
    Font(resId = R.font.roboto_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.roboto_italic, style = FontStyle.Italic),
    Font(resId = R.font.roboto_light, weight = FontWeight.Light),
    Font(resId = R.font.roboto_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
    Font(resId = R.font.roboto_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.roboto_thin, weight = FontWeight.Thin),
    Font(resId = R.font.roboto_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

///** [Thin] */
//@Stable
//val W100 = FontWeight(100)
///** [ExtraLight] */
//@Stable
//val W200 = FontWeight(200)
///** [Light] */
//@Stable
//val W300 = FontWeight(300)
///** [Normal] / regular / plain */
//@Stable
//val W400 = FontWeight(400)
///** [Medium] */
//@Stable
//val W500 = FontWeight(500)
///** [SemiBold] */
//@Stable
//val W600 = FontWeight(600)
///** [Bold] */
//@Stable
//val W700 = FontWeight(700)
///** [ExtraBold] */
//@Stable
//val W800 = FontWeight(800)
///** [Black] */
//@Stable
//val W900 = FontWeight(900)
// Set of Material typography styles to start with

//val displayLarge: TextStyle = TypographyTokens.DisplayLarge,
//val displayMedium: TextStyle = TypographyTokens.DisplayMedium,
//val displaySmall: TextStyle = TypographyTokens.DisplaySmall,
//val headlineLarge: TextStyle = TypographyTokens.HeadlineLarge,
//val headlineMedium: TextStyle = TypographyTokens.HeadlineMedium,
//val headlineSmall: TextStyle = TypographyTokens.HeadlineSmall,
//val titleLarge: TextStyle = TypographyTokens.TitleLarge,
//val titleMedium: TextStyle = TypographyTokens.TitleMedium,
//val titleSmall: TextStyle = TypographyTokens.TitleSmall,
//val bodyLarge: TextStyle = TypographyTokens.BodyLarge,
//val bodyMedium: TextStyle = TypographyTokens.BodyMedium,
//val bodySmall: TextStyle = TypographyTokens.BodySmall,
//val labelLarge: TextStyle = TypographyTokens.LabelLarge,
//val labelMedium: TextStyle = TypographyTokens.LabelMedium,
//val labelSmall: TextStyle = TypographyTokens.LabelSmall,

val Typography = Typography(
    //400 Normal
    //display content
    bodyMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.23.sp,
        letterSpacing = 0.15.sp
    ),

    //400 Normal
    bodySmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.06.sp,
    ),

    //400 Normal
    displaySmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
    ),

    //500 Medium
    //stockCode
    labelSmall = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.64.sp,
        letterSpacing = 0.sp
    ),

    //600 Normal
    displayMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.23.sp,
        letterSpacing = 0.15.sp
    ),

    //700 Bold
    titleMedium = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.62.sp,
        letterSpacing = 0.sp
    ),

    //700 Bold
    //stockName
    titleLarge = TextStyle(
        fontFamily = roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.13.sp,
        letterSpacing = 0.sp
    ),
)