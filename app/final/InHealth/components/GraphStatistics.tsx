import * as React from "react";
import { StyleSheet, View, Text } from "react-native";
import { Image } from "expo-image";
import { Color, FontFamily, FontSize } from "../GlobalStyles";

const GraphStatistics = () => {
  return (
    <View style={styles.mobileMobilechartsGridAnd}>
      <View style={[styles.chart, styles.bgPosition]}>
        <View style={[styles.bg, styles.bgPosition]} />
        <View style={styles.chart1}>
          <View style={[styles.grid, styles.bgPosition]}>
            <View style={styles.vericalLines}>
              <View style={[styles.row2, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>500</Text>
                  <View style={[styles.line, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
              <View style={[styles.row21, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>500</Text>
                  <View style={[styles.line, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
              <View style={[styles.row22, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>400</Text>
                  <View style={[styles.line, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
              <View style={[styles.row23, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>300</Text>
                  <View style={[styles.line, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
              <View style={[styles.row24, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>200</Text>
                  <View style={[styles.line, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
              <View style={[styles.row25, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>100</Text>
                  <View style={[styles.line, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
              <View style={[styles.row26, styles.rowPosition]}>
                <View style={[styles.row, styles.bgPosition]}>
                  <View style={[styles.container, styles.bgPosition1]} />
                  <Text style={styles.number}>0</Text>
                  <View style={[styles.line12, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                  <View style={[styles.line1, styles.linePosition]}>
                    <View style={[styles.rectangle, styles.textPosition2]} />
                  </View>
                </View>
              </View>
            </View>
            <View style={[styles.text, styles.textPosition2]}>
              <Text style={[styles.one, styles.oneTypo]}>One</Text>
              <Text style={[styles.five, styles.oneTypo]}>Five</Text>
              <Text style={[styles.four, styles.twoTypo]}>Four</Text>
              <Text style={[styles.three, styles.twoTypo]}>Three</Text>
              <Text style={[styles.two, styles.twoTypo]}>Two</Text>
            </View>
          </View>
          <Image
            style={[styles.barCharts100PercentStac, styles.bgPosition]}
            contentFit="cover"
            source={require("../assets/bar-charts--100-percent-stacked-bar-chart.png")}
          />
        </View>
        <Text style={styles.text1}>0</Text>
        <Text style={styles.k}>1K</Text>
        <Text style={[styles.k1, styles.k1Position]}>2K</Text>
        <Text style={[styles.k2, styles.k1Position]}>3K</Text>
        <Text style={[styles.k3, styles.k1Position]}>4K</Text>
        <Text style={[styles.text2, styles.textPosition]}>Sun</Text>
        <Text style={[styles.text3, styles.textPosition1]}>Mon</Text>
        <Text style={[styles.text4, styles.textPosition1]}>Tue</Text>
        <Text style={[styles.text5, styles.textPosition1]}>Wed</Text>
        <Text style={[styles.text6, styles.textPosition]}>Thu</Text>
        <Text style={[styles.text7, styles.textPosition1]}>Fri</Text>
        <Text style={[styles.text8, styles.textPosition1]}>Sat</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  bgPosition: {
    bottom: "0%",
    left: "0%",
    position: "absolute",
  },
  rowPosition: {
    width: "13.6%",
    bottom: "0%",
    top: "0%",
    height: "100%",
    position: "absolute",
  },
  bgPosition1: {
    right: "0%",
    width: "100%",
  },
  linePosition: {
    width: 1,
    bottom: 24,
    top: 0,
    position: "absolute",
  },
  textPosition2: {
    bottom: 0,
    top: 0,
    position: "absolute",
  },
  oneTypo: {
    color: Color.dimgray_100,
    fontFamily: FontFamily.montserratBold,
    fontWeight: "700",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    lineHeight: 20,
    left: "0%",
    width: "100%",
    position: "absolute",
  },
  twoTypo: {
    top: "50%",
    color: Color.dimgray_100,
    fontFamily: FontFamily.montserratBold,
    fontWeight: "700",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    lineHeight: 20,
    left: "0%",
    width: "100%",
    position: "absolute",
  },
  k1Position: {
    width: "4%",
    left: "5.6%",
    color: Color.darkgray_100,
    fontFamily: FontFamily.montserratRegular,
    top: "50%",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  textPosition: {
    width: "5.33%",
    marginTop: 83.5,
    fontFamily: FontFamily.montserratRegular,
    top: "50%",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  textPosition1: {
    marginTop: 83.5,
    color: Color.darkgray_100,
    fontFamily: FontFamily.montserratRegular,
    top: "50%",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  bg: {
    height: "110.59%",
    top: "-10.59%",
    left: "0%",
    right: "0%",
    width: "100%",
  },
  container: {
    height: "94%",
    bottom: "6%",
    left: "0%",
    top: "0%",
    position: "absolute",
  },
  number: {
    fontSize: FontSize.textCaptionSemiBold_size,
    fontWeight: "500",
    fontFamily: FontFamily.montserratMedium,
    color: Color.darkgray_200,
    width: 31,
    textAlign: "left",
    lineHeight: 20,
    bottom: 0,
    left: 0,
    position: "absolute",
  },
  rectangle: {
    borderStyle: "solid",
    borderColor: "#e9ebf1",
    borderWidth: 1,
    left: 0,
    right: 0,
  },
  line: {
    display: "none",
    left: 0,
  },
  line1: {
    right: 0,
  },
  row: {
    overflow: "hidden",
    left: "0%",
    right: "0%",
    width: "100%",
    top: "0%",
    height: "100%",
    bottom: "0%",
  },
  row2: {
    right: "18.73%",
    left: "67.67%",
  },
  row21: {
    right: "5.14%",
    left: "81.27%",
  },
  row22: {
    right: "32.33%",
    left: "54.08%",
  },
  row23: {
    right: "45.92%",
    left: "40.48%",
  },
  row24: {
    right: "59.52%",
    left: "26.89%",
  },
  row25: {
    right: "73.11%",
    left: "13.29%",
  },
  line12: {
    left: 0,
  },
  row26: {
    right: "86.4%",
    left: "0%",
  },
  vericalLines: {
    bottom: -24,
    left: 0,
    top: 0,
    overflow: "hidden",
    position: "absolute",
    width: 331,
  },
  one: {
    top: -1,
  },
  five: {
    bottom: 1,
  },
  four: {
    marginTop: 43,
  },
  three: {
    marginTop: -11,
  },
  two: {
    marginTop: -65,
  },
  text: {
    left: -58,
    width: 50,
    overflow: "hidden",
  },
  grid: {
    width: "83.08%",
    right: "16.92%",
    left: "0%",
    top: "0%",
    height: "100%",
    bottom: "0%",
  },
  barCharts100PercentStac: {
    maxWidth: "100%",
    maxHeight: "100%",
    overflow: "hidden",
    left: "0%",
    right: "0%",
    width: "100%",
    top: "0%",
    height: "100%",
    bottom: "0%",
  },
  chart1: {
    top: 1,
    bottom: 48,
    left: 44,
    overflow: "hidden",
    right: 0,
    position: "absolute",
  },
  text1: {
    marginTop: 60.5,
    width: "2.13%",
    left: "7.47%",
    color: Color.darkgray_100,
    fontFamily: FontFamily.montserratRegular,
    top: "50%",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  k: {
    marginTop: 14.5,
    left: "5.6%",
    width: "3.47%",
    color: Color.darkgray_100,
    fontFamily: FontFamily.montserratRegular,
    top: "50%",
    fontSize: FontSize.textSmallTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  k1: {
    marginTop: -31.5,
  },
  k2: {
    marginTop: -77.5,
  },
  k3: {
    marginTop: -123.5,
  },
  text2: {
    left: "14.13%",
    color: Color.darkgray_100,
  },
  text3: {
    width: "6.13%",
    left: "26.4%",
  },
  text4: {
    width: "5.07%",
    left: "38.93%",
  },
  text5: {
    width: "6.4%",
    left: "50.13%",
  },
  text6: {
    left: "62.67%",
    color: Color.black,
  },
  text7: {
    left: "75.73%",
    width: "3.47%",
    marginTop: 83.5,
  },
  text8: {
    width: "4.53%",
    left: "87.73%",
  },
  chart: {
    left: "0%",
    right: "0%",
    width: "100%",
    top: "0%",
    height: "100%",
    bottom: "0%",
  },
  mobileMobilechartsGridAnd: {
    height: 207,
    marginTop: 20,
    width: 331,
  },
});

export default GraphStatistics;
