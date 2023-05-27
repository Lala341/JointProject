import * as React from "react";
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import { RouteProp, useNavigation, useRoute } from "@react-navigation/native";
import { FontFamily, Color, Border, FontSize } from "../GlobalStyles";
import Menu from "../components/Menu";
import { Alert } from "react-native";
import Constants from 'expo-constants';
import * as FileSystem from 'expo-file-system';

export type RootStackParamList = {
  Home: { fileUri: string , data: string};
};
export type RootRouteProps<RouteName extends keyof RootStackParamList> = RouteProp<
  RootStackParamList,
  RouteName
>;
const Home  = (  ) => {
  const navigation = useNavigation();
  const route = useRoute<RootRouteProps<'Home'>>();
  const [isWriting, setIsWriting] = React.useState(false);

  const createTwoButtonAlert = () =>
  Alert.alert('Success', 'File sent successfully.', [
    {text: 'OK', onPress: () => {console.log('OK Pressed');}},
  ]);
  const sendFileToServer = async () => {


    var data= route.params.data;

    console.log("voy");
    const now = new Date();
    const deviceId = Constants.installationId;
 // const deviceId = "1e9c1862-ec25-4cde-a689-38ab696ccba1";
    const filename = `/sensor-data_${deviceId}_${now.toISOString().split(".")[0]}.txt`;

    const fileUri = FileSystem.documentDirectory + filename;
    
    // Write concatenated data back to file
    await FileSystem.writeAsStringAsync(
      fileUri,
      data,
      { encoding: FileSystem.EncodingType.UTF8 }
    );
     setIsWriting(false);

    if (fileUri) {
      const formData = new FormData();
      var values=fileUri.split("/");
      var nam=values[values.length-1].replace(".txt","");
      nam=nam.replace(":","-");
      nam=nam.replace(":","-");
      console.log(nam);
      console.log(values);

      formData.append('file', {uri: fileUri, type: "text/plain", name: nam+".txt"});
      formData.append('name', nam);

      const options = {
        method: 'POST',
        headers: {
          'Content-Type': 'multipart/form-data',
        },
        body: formData
      };
      const response = await fetch('http://192.168.0.22:8090/', options);

      if (response.ok) {
        console.log('File sent to server');
        createTwoButtonAlert();
      } else {

        console.error('Failed to send file to server');
      }
    }
  };
  return (
    <View style={styles.home}>
      <View style={styles.frameParent}>
        <View>
          <Text style={styles.hello}>Hello,</Text>
          <Text style={[styles.laura, styles.lauraTypo]}>Laura</Text>
        </View>
        <View style={styles.frameGroup}>
        <View style={styles.notification1Parent}>
         <Menu/>
        </View>
          <View style={[styles.todayParent, styles.parentPosition1]}>
            <Text style={[styles.today, styles.todayPosition]}>Today</Text>
            <View style={[styles.frameContainer, styles.parentPosition1]}>
              <View style={[styles.frameView, styles.frameLayout]}>
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
                  <Text style={[styles.text, styles.textTypo]}>78</Text>
                  <Text style={[styles.min, styles.minPosition]}>  min</Text>
                </View>
              </View>
              <View style={[styles.frameParent1, styles.frameLayout]}>
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
                  <Text style={[styles.walking, styles.kmClr]}>Walking</Text>
                </View>
                <View style={[styles.group, styles.parentPosition]}>
                  <Text style={[styles.text1, styles.kmClr]}>10</Text>
                  <Text style={[styles.km, styles.kmClr]}>  km</Text>
                </View>
              </View>
              <View style={[styles.frameParent2, styles.frameParentPosition]}>
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
                  <Text style={[styles.text2, styles.min1Clr]}>24</Text>
                  <Text style={[styles.min1, styles.min1Clr]}>  min</Text>
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
                    source={require("../assets/sleep-sleeping-rest-11.png")}
                  />
                  <Text style={[styles.sleep, styles.hrsClr]}>Sleep</Text>
                </View>
                <View style={[styles.parent1, styles.parentPosition]}>
                  <Text style={[styles.text3, styles.hrsClr]}>8</Text>
                  <Text style={[styles.hrs, styles.hrsClr]}> hrs</Text>
                </View>
              </View>
            </View>
          </View>
          <Pressable style={[styles.saturday, styles.saturdayLayout]}
          onPress={()=>sendFileToServer()}>
            <Image
              style={[styles.saturdayChild, styles.saturdayLayout]}
              contentFit="cover"
              source={require("../assets/rectangle-65.png")}
              
            />
            <Text style={styles.sendData}>Send Data</Text>
          </Pressable>
          <Pressable style={[styles.saturday2, styles.saturdayLayout2]}>
           <Image
            style={[styles.syncIcon, styles.iconCommon]}
            contentFit="cover"
            source={require("../assets/sync1.png")}
          />
          </Pressable>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  notification1Parent: {
    top: 420,
    position: "absolute",

  },
  lauraTypo: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    textAlign: "left",
    color: Color.bl,
  },
  parentPosition1: {
    left: 0,
    width: 295,
    position: "absolute",
  },
  notification1Position: {
    top: 16,
    left: "50%",
    position: "absolute",
  },
  iconCommon: {
    opacity: 0.5,
    overflow: "hidden",
  },
  todayPosition: {
    left: "50%",
    position: "absolute",
  },
  iconLayout: {
    height: "100%",
    width: "100%",
  },
  frameLayout: {
    height: 120,
    width: 137,
    borderRadius: Border.br_xl,
    marginLeft: -147.5,
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
    top: 53,
    left: 17,
    position: "absolute",
  },
  textTypo: {
    fontSize: FontSize.size_21xl,
    top: 0,
    left: "50%",
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    position: "absolute",
  },
  minPosition: {
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    fontSize: FontSize.size_base,
    left: "50%",
    position: "absolute",
  },
  kmClr: {
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
  saturdayLayout: {
    height: 40,
    width: 104,
    position: "absolute",
  },
  saturdayLayout2: {
    height: 40,
    width: 40,
    position: "absolute",
  },
  hello: {
    fontSize: FontSize.size_11xl,
    width: 295,
    textAlign: "left",
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  laura: {
    fontSize: 48,
    marginTop: 4,
    width: 295,
  },
  icon: {
    marginLeft: 62.5,
    height: "100%",
    width: "100%",
  },
  notification1: {
    width: 24,
    height: 24,
  },
  icon1: {
    marginLeft: 123.5,
    height: "100%",
    width: "100%",
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
    top: 0,
    height: 56,
  },
  icon3: {
    marginLeft: -147.5,
    overflow: "hidden",
  },
  icon4: {
    marginLeft: -87.5,
    height: "100%",
    width: "100%",
  },
  today: {
    fontSize: FontSize.size_lg,
    opacity: 0.7,
    marginLeft: -147.5,
    top: 0,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    textAlign: "left",
    color: Color.bl,
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
  frameView: {
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
    fontSize: FontSize.size_21xl,
    top: 0,
    left: "50%",
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    position: "absolute",
  },
  km: {
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
  frameParent1: {
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
    fontSize: FontSize.size_21xl,
    top: 0,
    left: "50%",
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
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
  frameParent2: {
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
    fontSize: FontSize.size_21xl,
    top: 0,
    left: "50%",
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
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
  frameParent3: {
    backgroundColor: Color.cornflowerblue_200,
    top: 140,
  },
  frameContainer: {
    top: 47,
    height: 260,
  },
  todayParent: {
    height: 307,
    top: 0,
  },
  saturdayChild: {
    borderRadius: Border.br_xl,
    height: 40,
    width: 104,
    top: 0,
    left: 0,
  },
  sendData: {
    top: 11,
    fontSize: FontSize.textSmallTextSemiBold_size,
    letterSpacing: 0.2,
    textTransform: "capitalize",
    fontWeight: "600",
    fontFamily: FontFamily.montserratSemibold,
    color: Color.whiteColor,
    left: 17,
    textAlign: "left",
    position: "absolute",
  },
  saturday: {
    top: 319,
    left: 191,
  },
  saturday2: {
    top: 10,
    left: 121,
  },
  syncIcon: {
    marginLeft: 127.5,
    top: 3,
    width: 20,
    height: 20,
    left: "50%",
    position: "absolute",
  },
  frameGroup: {
    height: 700,
    marginTop: 98,
    width: 295,
  },
  frameParent: {
    top: 106,
    left: 40,
    justifyContent: "center",
    alignItems: "center",
    position: "absolute",
  },
  home: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});
export default Home;
