import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import { useNavigation } from "@react-navigation/native";
import ChangeIntervalQuery from "../components/ChangeIntervalQuery";
import ChangeSpecialization from "../components/ChangeSpecialization";
import { Border, FontSize, FontFamily, Color } from "../GlobalStyles";
import Menu from "../components/Menu";

import { useEffect, useState } from "react";
import Constants from 'expo-constants';
import dayjs from 'dayjs';

const StatisticsIII = () => {
  const navigation = useNavigation();
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

  return (
    <View style={styles.statisticsIii}>
      <View style={[styles.frameParent, styles.groupPosition]}>
        <View style={styles.frameGroup}>
          <View style={styles.statisticsWrapper}>
            <Text style={styles.statistics}>Statistics</Text>
          </View>
          <ChangeIntervalQuery rectangle65MarginTop={18} />
          <View style={[styles.groupWrapper, styles.groupLayout1]}>
            <View style={[styles.groupContainer, styles.groupLayout1]}>
              <View style={[styles.groupContainer, styles.groupLayout1]}>
                <View style={[styles.groupView, styles.groupPosition]}>
                  <View
                    style={[styles.rectangleParent, styles.groupParentPosition]}
                  >
                    <View style={[styles.groupChild, styles.groupLayout]} />
                    <View style={styles.ic24Heart1Parent}>
                      <Image
                        style={styles.ic24Heart1Icon}
                        contentFit="cover"
                        source={require("../assets/ic24heart-11.png")}
                      />
                      <Text style={[styles.walking, styles.walkingTypo]}>
                        WALKING
                      </Text>
                    </View>
                    <View style={[styles.parent, styles.parentPosition]}>
                      <Text style={[styles.text, styles.textTypo]}>{activities?activities["WALKING"]:0}</Text>
                      <Text style={[styles.min, styles.minPosition]}> min</Text>
                    </View>
                  </View>
                  <View
                    style={[styles.rectangleGroup, styles.groupParentPosition]}
                  >
                    <View style={[styles.groupItem, styles.groupLayout]} />
                    <View style={styles.ic24Heart1Parent}>
                      <Image
                        style={styles.ic24Heart1Icon}
                        contentFit="cover"
                        source={require("../assets/ic24flag-11.png")}
                      />
                      <Text style={[styles.walkingDownstairs, styles.min1Clr]}>
                        {`WALKING `}DOWNSTAIRS
                      </Text>
                    </View>
                    <View style={[styles.group, styles.parentPosition]}>
                      <Text style={[styles.text1, styles.min1Clr]}>{activities?activities["WALKING_UPSTAIRS"]:0}</Text>
                      <Text style={[styles.min1, styles.min1Clr]}>min</Text>
                    </View>
                  </View>
                  <View
                    style={[
                      styles.rectangleContainer,
                      styles.rectanglePosition,
                    ]}
                  >
                    <View style={[styles.groupInner, styles.groupLayout]} />
                    <View style={styles.ic24Heart1Parent}>
                      <Image
                        style={styles.ic24Heart1Icon}
                        contentFit="cover"
                        source={require("../assets/ic24bolt-12.png")}
                      />
                      <Text style={[styles.walkingUpstairs, styles.min2Clr]}>
                        {`WALKING `}UPSTAIRS
                      </Text>
                    </View>
                    <View style={[styles.parent, styles.parentPosition]}>
                      <Text style={[styles.text2, styles.min2Clr]}>{activities?activities["WALKING_DOWNSTAIRS"]:0}</Text>
                      <Text style={[styles.min2, styles.min2Clr]}> min</Text>
                    </View>
                  </View>
                  <View
                    style={[styles.rectangleParent1, styles.rectanglePosition]}
                  >
                    <View style={styles.rectangleView} />
                    <View style={styles.ic24Heart1Parent}>
                      <Image
                        style={styles.ic24Heart1Icon}
                        contentFit="cover"
                        source={require("../assets/sleep-sleeping-rest-1.png")}
                      />
                      <Text style={[styles.sitting, styles.min3Clr]}>
                        SITTING
                      </Text>
                    </View>
                    <View style={[styles.parent1, styles.parentPosition]}>
                      <Text style={[styles.text3, styles.min3Clr]}>{activities?activities["SITTING"]:0}</Text>
                      <Text style={[styles.min3, styles.min3Clr]}>min</Text>
                    </View>
                  </View>
                </View>
                <View style={[styles.groupParent1, styles.groupParentPosition]}>
                  <View
                    style={[styles.rectangleParent, styles.groupParentPosition]}
                  >
                    <View style={[styles.groupChild, styles.groupLayout]} />
                    <View style={styles.ic24Heart1Parent}>
                      <Image
                        style={styles.ic24Heart1Icon}
                        contentFit="cover"
                        source={require("../assets/ic24heart-11.png")}
                      />
                      <Text style={[styles.walking, styles.walkingTypo]}>
                        STANDING
                      </Text>
                    </View>
                    <View style={[styles.parent, styles.parentPosition]}>
                      <Text style={[styles.text, styles.textTypo]}>{activities?activities["STANDING"]:0}</Text>
                      <Text style={[styles.min, styles.minPosition]}> min</Text>
                    </View>
                  </View>
                  <View
                    style={[
                      styles.rectangleContainer,
                      styles.rectanglePosition,
                    ]}
                  >
                    <View style={[styles.groupInner, styles.groupLayout]} />
                    <View style={styles.ic24Heart1Parent}>
                      <Image
                        style={styles.ic24Heart1Icon}
                        contentFit="cover"
                        source={require("../assets/ic24bolt-11.png")}
                      />
                      <Text style={[styles.walkingUpstairs, styles.min2Clr]}>
                        LAYING
                      </Text>
                    </View>
                    <View style={[styles.parent, styles.parentPosition]}>
                      <Text style={[styles.text2, styles.min2Clr]}>{activities?activities["LAYING"]:0}</Text>
                      <Text style={[styles.min2, styles.min2Clr]}> min</Text>
                    </View>
                  </View>
                </View>
              </View>
            </View>
          </View>
        </View>
        <ChangeSpecialization
          overview="Overview"
          onSaturdayPress={() => navigation.navigate("Statistics")}
          onSaturdayPress1={() => navigation.navigate("StatisticsII")}
        />
       <View style={styles.notification1Parent}>
         <Menu/>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  groupPosition: {
    left: 0,
    position: "absolute",
  },
  groupLayout1: {
    height: 456,
    width: 334,
  },
  groupParentPosition: {
    height: 138,
    left: 0,
    position: "absolute",
  },
  groupLayout: {
    opacity: 0.1,
    borderRadius: Border.br_xl,
    height: 138,
    width: 155,
    top: 0,
    left: 0,
    position: "absolute",
  },
  walkingTypo: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  parentPosition: {
    height: 60,
    left: 19,
    top: 61,
    position: "absolute",
  },
  textTypo: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    height: 60,
    top: 0,
    left: 0,
    position: "absolute",
  },
  minPosition: {
    width: 36,
    left: 51,
    height: 24,
    fontFamily: FontFamily.dMSansRegular,
    top: 24,
    fontSize: FontSize.size_base,
    position: "absolute",
  },
  min1Clr: {
    color: Color.lightseagreen_100,
    textAlign: "left",
  },
  rectanglePosition: {
    left: 179,
    height: 138,
    width: 155,
    position: "absolute",
  },
  min2Clr: {
    color: Color.mediumslateblue_100,
    textAlign: "left",
  },
  min3Clr: {
    color: Color.cornflowerblue_100,
    textAlign: "left",
  },
  wrapperPosition: {
    left: "50%",
    position: "absolute",
  },
  iconLayout: {
    opacity: 0.5,
    height: "100%",
    overflow: "hidden",
    width: "100%",
  },
  iconLayout1: {
    height: "100%",
    width: "100%",
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
  groupChild: {
    backgroundColor: Color.palevioletred_100,
  },
  ic24Heart1Icon: {
    width: 16,
    height: 16,
    overflow: "hidden",
  },
  walking: {
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  ic24Heart1Parent: {
    top: 18,
    left: 18,
    flexDirection: "row",
    alignItems: "center",
    position: "absolute",
  },
  text: {
    width: 53,
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  min: {
    height: 24,
    color: Color.palevioletred_100,
    textAlign: "left",
  },
  parent: {
    width: 87,
  },
  rectangleParent: {
    width: 155,
    height: 138,
    top: 0,
  },
  groupItem: {
    backgroundColor: Color.lightseagreen_100,
  },
  walkingDownstairs: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text1: {
    width: 49,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    height: 60,
    top: 0,
    left: 0,
    position: "absolute",
  },
  min1: {
    left: 49,
    width: 31,
    height: 24,
    fontFamily: FontFamily.dMSansRegular,
    top: 24,
    fontSize: FontSize.size_base,
    position: "absolute",
  },
  group: {
    width: 79,
  },
  rectangleGroup: {
    top: 161,
    width: 155,
    height: 138,
  },
  groupInner: {
    backgroundColor: Color.mediumslateblue_100,
  },
  walkingUpstairs: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text2: {
    width: 55,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    height: 60,
    top: 0,
    left: 0,
    position: "absolute",
  },
  min2: {
    height: 24,
    width: 36,
    left: 51,
    fontFamily: FontFamily.dMSansRegular,
    top: 24,
    fontSize: FontSize.size_base,
    position: "absolute",
  },
  rectangleContainer: {
    top: 0,
  },
  rectangleView: {
    backgroundColor: Color.cornflowerblue_200,
    borderRadius: Border.br_xl,
    height: 138,
    width: 155,
    top: 0,
    left: 0,
    position: "absolute",
  },
  sitting: {
    marginLeft: 7,
    fontSize: FontSize.size_base,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  text3: {
    width: 29,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_21xl,
    height: 60,
    top: 0,
    left: 0,
    position: "absolute",
  },
  min3: {
    left: 30,
    width: 45,
    height: 24,
    fontFamily: FontFamily.dMSansRegular,
    top: 24,
    fontSize: FontSize.size_base,
    position: "absolute",
  },
  parent1: {
    width: 75,
  },
  rectangleParent1: {
    top: 161,
  },
  groupView: {
    height: 299,
    top: 0,
    width: 334,
  },
  groupParent1: {
    top: 318,
    width: 334,
  },
  groupContainer: {
    top: 0,
    left: 0,
    position: "absolute",
  },
  groupWrapper: {
    marginTop: 18,
  },
  frameGroup: {
    justifyContent: "center",
    alignItems: "center",
  },
  icon: {
    marginLeft: 62.5,
  },
  notification1: {
    top: 16,
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
  },
  wrapper: {
    width: 56,
    height: 56,
    top: 0,
  },
  icon3: {
    marginLeft: -147.5,
    overflow: "hidden",
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
  },
  statisticsIii: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});

export default StatisticsIII;
