import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import { useNavigation } from "@react-navigation/native";
import ChangeIntervalQuery from "../components/ChangeIntervalQuery";
import ChangeSpecialization from "../components/ChangeSpecialization";
import { Border, Color, FontFamily, FontSize } from "../GlobalStyles";
import Menu from "../components/Menu";
import { useEffect, useState } from "react";
import Constants from 'expo-constants';
import dayjs from 'dayjs';

const StatisticsII = () => {
  const navigation = useNavigation();

  const [steps, setSteps] = useState(0);
  const [distance, setDistance] = useState(0);
  const [activities, setActivities] = useState();
  const today = dayjs().subtract(1, 'day').format('YYYY-MM-DD');
  const tomorrow = dayjs().add(1, 'day').format('YYYY-MM-DD');


  useEffect(() => {
    // Fetch the number of steps from the REST endpoint
    fetch('http://192.168.0.22:8090/analytics/activity?user=' + Constants.installationId + '&startDate=' + today + '&endDate=' + tomorrow)
      .then(response => response.json())
      .then(data => {

        console.log(Constants.installationId);
        console.log(data);

        if(data!=null&& data.length>0){

          var acts= {
            "WALKING": 0,
            "WALKING_UPSTAIRS": 0,
            "WALKING_DOWNSTAIRS": 0,
            "SITTING": 0,
            "STANDING": 0,
            "LAYING": 0
          };

            for(var i =0;i<data.length;i++){
              acts[data[i]["activity"]]=  data[i]["count"];
             
            }

        }
        setActivities(acts);
        console.log(acts);

      })
      .catch(error => {
        console.error('Error fetching activities:', error);
      });
  }, []); // Empty dependency array to run the effect only once on component mount

  useEffect(() => {
    // Fetch the number of steps from the REST endpoint
    fetch('http://192.168.0.22:8090/analytics/steps/sum?user=' + Constants.installationId + '&startDate=' + today + '&endDate=' + tomorrow)
      .then(response => response.json())
      .then(data => {
        console.log(Constants.installationId)
        const stepsCount = parseFloat(data); // Assuming the response contains a "steps" property
        setSteps(stepsCount);
      })
      .catch(error => {
        console.error('Error fetching steps:', error);
      });
  }, []); // Empty dependency array to run the effect only once on component mount

  useEffect(() => {
    // Fetch the number of steps from the REST endpoint
    fetch('http://192.168.0.22:8090/analytics/distance/sum?user=' + Constants.installationId + '&startDate=' + today + '&endDate=' + tomorrow)
      .then(response => response.json())
      .then(data => {
        console.log(Constants.installationId)
        const totalDistance = parseFloat(data); // Assuming the response contains a "steps" property
        setDistance(totalDistance);
      })
      .catch(error => {
        console.error('Error fetching distance:', error);
      });
  }, []); // Empty dependency array to run the effect only once on component mount


  return (
    <View style={styles.statisticsIi}>
      <View style={styles.frameParent}>
        <View style={styles.frameGroup}>
          <View style={styles.frameGroup}>
            <View style={styles.statisticsWrapper}>
              <Text style={styles.statistics}>Statistics</Text>
            </View>
            <ChangeIntervalQuery />
            <View style={styles.topicfashionParent}>
              <View style={[styles.topicfashion, styles.topicfashionLayout]}>
                <View style={[styles.rectangle, styles.rectanglePosition]} />
                <Text style={[styles.fashion, styles.fashionTypo]}>
                  Total Steps
                </Text>
                <Text style={[styles.articles, styles.articlesTypo]}>{steps}</Text>
                <Image
                  style={styles.ovalIcon}
                  contentFit="cover"
                  source={require("../assets/oval.png")}
                />
                <Text style={[styles.steps, styles.kmTypo]}>Steps</Text>
                <Image
                  style={[
                    styles.footstepsSilhouetteVariant1Icon,
                    styles.iconLayout1,
                  ]}
                  contentFit="cover"
                  source={require("../assets/footstepssilhouettevariant-1.png")}
                />
              </View>
              <View style={[styles.topicfashion1, styles.topicfashionLayout]}>
                <View style={[styles.rectangle1, styles.rectanglePosition]} />
                <Text style={[styles.fashion1, styles.fashionTypo]}>
                  Distance
                </Text>
                <Text style={[styles.articles1, styles.articlesTypo]}>{distance.toFixed()}</Text>
                <Image
                  style={styles.ovalIcon}
                  contentFit="cover"
                  source={require("../assets/oval.png")}
                />
                <Text style={[styles.km, styles.kmTypo]}>m</Text>
                <Image
                  style={[styles.distance1Icon, styles.iconLayout1]}
                  contentFit="cover"
                  source={require("../assets/distance-1.png")}
                />
              </View>
            </View>
            <View style={styles.frameView}>
              <View style={[styles.frameParent1, styles.frameParentLayout]}>
                <View
                  style={[
                    styles.ic24Heart1Parent,
                    styles.notification1Position,
                  ]}
                >
                  <Image
                    style={styles.ic24Heart1Icon}
                    contentFit="cover"
                    source={require("../assets/ic24heart-12.png")}
                  />
                  <Text style={[styles.exercise, styles.sleepTypo]}>
                    Exercise
                  </Text>
                </View>
                <View style={[styles.parent, styles.parentPosition]}>
                  <Text style={[styles.text, styles.textTypo]}>{activities?activities["WALKING"]+activities["WALKING_UPSTAIRS"]+activities["WALKING_DOWNSTAIRS"]:0}</Text>
                  <Text style={[styles.min, styles.minPosition]}> min</Text>
                </View>
              </View>
              <View style={[styles.frameParent2, styles.frameParentLayout]}>
                <View
                  style={[
                    styles.ic24Heart1Parent,
                    styles.notification1Position,
                  ]}
                >
                  <Image
                    style={styles.ic24Heart1Icon}
                    contentFit="cover"
                    source={require("../assets/ic24flag-12.png")}
                  />
                  <Text style={[styles.walking, styles.km1Clr]}>Walking</Text>
                </View>
                <View style={[styles.group, styles.parentPosition]}>
                  <Text style={[styles.text1, styles.km1Clr]}>{activities?activities["WALKING"]:0}</Text>
                  <Text style={[styles.km1, styles.km1Clr]}> min</Text>
                </View>
              </View>
              <View style={[styles.frameParent3, styles.frameParentPosition]}>
                <View
                  style={[
                    styles.ic24Heart1Parent,
                    styles.notification1Position,
                  ]}
                >
                  <Image
                    style={styles.ic24Heart1Icon}
                    contentFit="cover"
                    source={require("../assets/ic24bolt-13.png")}
                  />
                  <Text style={[styles.inactivity, styles.min1Clr]}>
                    Inactivity
                  </Text>
                </View>
                <View style={[styles.parent, styles.parentPosition]}>
                  <Text style={[styles.text2, styles.min1Clr]}>{activities?activities["LAYING"]+activities["STANDING"]+activities["SITTING"]:0}</Text>
                  <Text style={[styles.min1, styles.min1Clr]}> min</Text>
                </View>
              </View>
              <View style={[styles.frameParent4, styles.frameParentPosition]}>
                <View
                  style={[
                    styles.ic24Heart1Parent,
                    styles.notification1Position,
                  ]}
                >
                  <Image
                    style={styles.ic24Heart1Icon}
                    contentFit="cover"
                    source={require("../assets/sleep-sleeping-rest-11.png")}
                  />
                  <Text style={[styles.sleep, styles.hrsClr]}>Sleep</Text>
                </View>
                <View style={[styles.parent1, styles.parentPosition]}>
                  <Text style={[styles.text3, styles.hrsClr]}>{activities?activities["LAYING"]:0}</Text>
                  <Text style={[styles.hrs, styles.hrsClr]}>min</Text>
                </View>
              </View>
            </View>
          </View>
          <ChangeSpecialization
            overview="Details"
            propLeft={30}
            onSaturdayPress={() => navigation.navigate("StatisticsIII")}
            onSaturdayPress1={() => navigation.navigate("Statistics")}
          />
        </View>
        <View style={styles.notification1Parent}>
         <Menu/>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  topicfashionLayout: {
    width: 164,
    overflow: "hidden",
    top: 0,
    height: 174,
    position: "absolute",
  },
  rectanglePosition: {
    borderRadius: Border.br_mini,
    left: "0%",
    bottom: "0%",
    right: "0.61%",
    top: "0%",
    width: "99.39%",
    height: "100%",
    position: "absolute",
  },
  fashionTypo: {
    color: Color.black,
    fontFamily: FontFamily.montserratMedium,
    fontSize: FontSize.textMediumTextSemiBold_size,
    top: "50%",
    marginTop: -6,
    textAlign: "left",
    fontWeight: "500",
    position: "absolute",
  },
  articlesTypo: {
    textAlign: "center",
    fontFamily: FontFamily.montserratSemibold,
    fontWeight: "600",
    fontSize: FontSize.size_5xl,
    marginTop: 21,
    color: Color.black,
    top: "50%",
    position: "absolute",
  },
  kmTypo: {
    color: Color.gray_100,
    fontFamily: FontFamily.montserratLight,
    fontWeight: "300",
    top: 137,
    fontSize: FontSize.textMediumTextSemiBold_size,
    textAlign: "left",
    position: "absolute",
  },
  iconLayout1: {
    height: 30,
    width: 30,
    top: 30,
    overflow: "hidden",
    position: "absolute",
  },
  frameParentLayout: {
    height: 120,
    width: 137,
    borderRadius: Border.br_xl,
    left: "50%",
    marginLeft: -147.5,
    position: "absolute",
  },
  notification1Position: {
    top: 16,
    left: "50%",
    position: "absolute",
  },
  sleepTypo: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  parentPosition: {
    height: 52,
    left: 17,
    top: 53,
    position: "absolute",
  },
  textTypo: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    left: "50%",
    top: 0,
    position: "absolute",
  },
  minPosition: {
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    fontSize: FontSize.size_base,
    left: "50%",
    position: "absolute",
  },
  km1Clr: {
    color: Color.lightseagreen_100,
    textAlign: "left",
  },
  frameParentPosition: {
    marginLeft: 10.5,
    height: 120,
    width: 137,
    borderRadius: Border.br_xl,
    left: "50%",
    position: "absolute",
  },
  min1Clr: {
    color: Color.mediumslateblue_100,
    textAlign: "left",
  },
  hrsClr: {
    color: Color.cornflowerblue_100,
    textAlign: "left",
  },
  iconLayout: {
    opacity: 0.5,
    height: "100%",
    overflow: "hidden",
    width: "100%",
  },
  wrapperPosition: {
    left: "50%",
    position: "absolute",
  },
  statistics: {
    top: 5,
    left: 20,
    fontSize: FontSize.size_11xl,
    color: Color.bl,
    width: 295,
    textAlign: "left",
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    position: "absolute",
  },
  statisticsWrapper: {
    width: 375,
    height: 50,
  },
  rectangle: {
    backgroundColor: "#d8efff",
  },
  fashion: {
    left: "31.71%",
  },
  articles: {
    left: "31.1%",
  },
  ovalIcon: {
    height: "29.31%",
    width: "31.1%",
    top: "11.49%",
    right: "34.76%",
    bottom: "59.2%",
    left: "34.15%",
    maxWidth: "100%",
    maxHeight: "100%",
    overflow: "hidden",
    position: "absolute",
  },
  steps: {
    left: 66,
  },
  footstepsSilhouetteVariant1Icon: {
    left: 66,
  },
  topicfashion: {
    overflow: "hidden",
    left: 0,
  },
  rectangle1: {
    backgroundColor: "#fcf2d5",
  },
  fashion1: {
    left: "32.93%",
  },
  articles1: {
    left: "40.85%",
  },
  km: {
    left: 71,
  },
  distance1Icon: {
    left: 67,
  },
  topicfashion1: {
    left: 179,
    overflow: "hidden",
  },
  topicfashionParent: {
    width: 343,
    marginTop: 18,
    height: 174,
  },
  ic24Heart1Icon: {
    width: 16,
    height: 16,
    overflow: "hidden",
  },
  exercise: {
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  ic24Heart1Parent: {
    marginLeft: -52.5,
    flexDirection: "row",
    alignItems: "center",
  },
  text: {
    marginLeft: -38.5,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  min: {
    marginLeft: 6.5,
    top: 21,
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  parent: {
    width: 77,
  },
  frameParent1: {
    backgroundColor: Color.palevioletred_200,
    top: 0,
  },
  walking: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text1: {
    marginLeft: -35,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    left: "50%",
    top: 0,
    position: "absolute",
  },
  km1: {
    marginLeft: 8,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    fontSize: FontSize.size_base,
    left: "50%",
    position: "absolute",
  },
  group: {
    width: 70,
  },
  frameParent2: {
    backgroundColor: Color.lightseagreen_200,
    top: 140,
  },
  inactivity: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text2: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    left: "50%",
    top: 0,
    position: "absolute",
    marginLeft: -38.5,
  },
  min1: {
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    fontSize: FontSize.size_base,
    left: "50%",
    position: "absolute",
    marginLeft: 6.5,
  },
  frameParent3: {
    backgroundColor: Color.mediumslateblue_200,
    top: 0,
  },
  sleep: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text3: {
    marginLeft: -24.5,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    left: "50%",
    top: 0,
    position: "absolute",
  },
  hrs: {
    marginLeft: 1.5,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    fontSize: FontSize.size_base,
    left: "50%",
    position: "absolute",
  },
  parent1: {
    width: 49,
  },
  frameParent4: {
    backgroundColor: Color.cornflowerblue_200,
    top: 140,
  },
  frameView: {
    height: 260,
    marginTop: 18,
    width: 295,
  },
  frameGroup: {
    justifyContent: "center",
    alignItems: "center",
  },
  icon: {
    marginLeft: 62.5,
  },
  notification1: {
    width: 24,
    height: 24,
  },
  icon1: {
    marginLeft: 123.5,
  },
  frameChild: {
    marginLeft: -137.5,
    top: 50,
    width: 4,
    height: 4,
  },
  icon2: {
    marginLeft: -28.5,
    height: "100%",
    width: "100%",
  },
  wrapper: {
    width: 56,
    height: 56,
    top: 0,
  },
  icon3: {
    marginLeft: -147.5,
    height: "100%",
    overflow: "hidden",
    width: "100%",
  },
  icon4: {
    marginLeft: -87.5,
  },
  notification1Parent: {
    top: 700,
    position: "absolute",

  },
  frameParent: {
    top: 44,
    alignItems: "center",
    left: 0,
    position: "absolute",
  },
  statisticsIi: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});

export default StatisticsII;
