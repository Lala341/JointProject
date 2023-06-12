import React, { useEffect, useState } from 'react';
import { Text, StyleSheet, View, Pressable } from "react-native";
import { Image } from "expo-image";
import Banner from "../components/Banner";
import Menu from "../components/Menu";
import { useNavigation } from "@react-navigation/native";
import { Color, Border, FontFamily, FontSize } from "../GlobalStyles";
import Constants from 'expo-constants';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Double } from 'react-native/Libraries/Types/CodegenTypes';

const Disease = () => {
  const navigation = useNavigation();
  const [idu, setIdU] = useState(false);  
  const [id, setId] = useState(Constants.installationId);
  const [predictions, setPredictions] = useState([]);


  useEffect(() => {
    if(!idu){
      setIdU(true);
    AsyncStorage.getItem('ID')
    .then(value => setId(value))
    .catch(error => console.log('Error getting ID from local storage:', error));
    }
  }, []);
  useEffect(() => {
    fetchPredictions();
  }, []);

  const fetchPredictions = async () => {
    try {
      const response = await fetch('http://192.168.0.22:8090/graph/getPrediction?person=' + id);
      const data = await response.json();
      setPredictions(data);
    } catch (error) {
      console.log('Error fetching predictions:', error);
    }
  };
  const getModule = ( txtt: String, prob : Double)=>{
    var txt= txtt.replaceAll("\"", "");
    if(prob>0.7){

      return (<Pressable key={txt}
        style={[ styles.groupChildLayout,  styles.data1]}
        onPress={() => navigation.navigate("DiseaseII")}
      >
        
          <View>
          <View
          style={[styles.ic24Heart1Parent, styles.ic24ParentPosition]}
        >
          <Image
            style={styles.ic24Bolt1Icon}
            contentFit="cover"
            source={require("../assets/ic24heart-1.png")}
          /><Text style={[styles.text, styles.textTypo]}>{txt}</Text>
              </View>
          <Text style={[styles.text, styles.textTypo]}>HIGH</Text>
          <Text style={[styles.text, styles.textTypo2]}>{prob.toFixed(3)}</Text>
      
        </View>
      
      </Pressable>);

    }else if (prob>0.4){
      return (<Pressable key={txt}
        style={[ styles.groupChildLayout,  styles.data2]}
        onPress={() => navigation.navigate("DiseaseII")}
      >
       <View>
          <View
          style={[styles.ic24Heart1Parent, styles.ic24ParentPosition]}
        >
          <Image
            style={styles.ic24Bolt1Icon}
            contentFit="cover"
            source={require("../assets/ic24heart-1.png")}
          /><Text style={[styles.text, styles.textTypo]}>{txt}</Text>
              </View>
          <Text style={[styles.text, styles.textTypo]}>MODERATE</Text>
          <Text style={[styles.text, styles.textTypo2]}>{prob.toFixed(3)}</Text>
      
        </View>
        
      </Pressable>);

    }
    return (<Pressable key={txt}
      style={[styles.groupChildLayout,  styles.data3]}
      onPress={() => navigation.navigate("DiseaseII")}
    >
        <View>

          <View
          style={[styles.ic24Heart1Parent, styles.ic24ParentPosition]}
        >
          <Image
            style={styles.ic24Bolt1Icon}
            contentFit="cover"
            source={require("../assets/ic24heart-1.png")}
          /><Text style={[styles.text, styles.textTypo]}>{txt}</Text>
              </View>
          <Text style={[styles.text, styles.textTypo]}>LOW</Text>
          <Text style={[styles.text, styles.textTypo2]}>{prob.toFixed(3)}</Text>
      
        </View>
    </Pressable>);

  }
  const getModuleBase = ( )=>{
    
      return (<Pressable 
        style={[ styles.groupChildLayout,  styles.data2]}
        onPress={() => navigation.navigate("DiseaseII")}
      >
       <View>
          <View
          style={[styles.ic24Heart1Parent, styles.ic24ParentPosition]}
        >
          <Image
            style={styles.ic24Bolt1Icon}
            contentFit="cover"
            source={require("../assets/ic24heart-1.png")}
          /><Text style={[styles.text, styles.textTypo]}>No risk factors found</Text>
              </View>
          <Text style={[styles.text, styles.textTypo2]}>Remember to answer the daily surveys.</Text>
      
        </View>
        
      </Pressable>);

    

  }
  return (
    <View style={styles.disease}>
      <View style={styles.frameParent}>
        <View>
          <Text style={[styles.predictiveInsights, styles.updatedTodayClr]}>
            Predictive Insights
          </Text>
        </View>
        <Banner />
        <View style={styles.frameGroup}>
        <View style={styles.notification1Parent}>
         <Menu/>
        </View>
          <View style={styles.updatedTodayParent}>
            <Text style={[styles.updatedToday, styles.textTypo1]}>
              Updated Today
            </Text>
            <View style={styles.groupParent}>
              
              {
                 predictions&&predictions.length>0?predictions.slice(0, 3).map((prediction: any) => (
                   getModule(prediction[0], prediction[1])
                 )):""
              }
              {
                predictions&&predictions.length==0?getModuleBase()
                :""
                
              }
            </View>
          </View>
          <Image
            style={styles.syncIcon}
            contentFit="cover"
            source={require("../assets/sync.png")}
          />
          <Pressable
            style={[styles.saturday, styles.saturdayLayout]}
            onPress={() => navigation.navigate("DiseaseII")}
          >
            <Image
              style={[styles.saturdayChild, styles.saturdayLayout]}
              contentFit="cover"
              source={require("../assets/rectangle-65.png")}
            />
            <Text style={styles.viewMore}>View More</Text>
          </Pressable>
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  notification1Parent: {
    top: 400,
    position: "absolute",

  },
  updatedTodayClr: {
    color: Color.bl,
    textAlign: "left",
  },
  groupChildLayout: {
    height: 97,
    width: 302,
    borderRadius: Border.br_xl,
    marginBottom: 10,
    padding: 10,
  },
  data1:{
    backgroundColor: Color.palevioletred_300,
   

  },
  data2:{
    backgroundColor: Color.mediumslateblue_300,

  },
  data3:{
    backgroundColor: Color.cornflowerblue_300,

  },
  
  walkingTypo: {
    marginLeft: 7,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  parentPosition: {
    height: 42,
    left: 34,
    position: "absolute",
  },
  textTypo: {
    fontFamily: FontFamily.dMSansRegular,
    fontWeight: "700",
    color: Color.whiteColor,
  },
  textTypo2: {
    fontFamily: FontFamily.dMSansRegular,
    fontWeight: "700",
    color: Color.gray_200,
  },
  textTypo1: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    color: Color.blackColor,
  },
  minPosition: {
    width: 71,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    left: 0,
    position: "absolute",
  },
  groupLayout: {
    height: 94,
    width: 305,
  },
  ic24ParentPosition: {
    flexDirection: "row",
    alignItems: "center",
  },
  min1Typo: {
    color: Color.palevioletred_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  groupInnerLayout: {
    height: 90,
    width: 305,
  },
  kmTypo: {
    color: Color.lightseagreen_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  saturdayLayout: {
    height: 40,
    width: 104,
    top: 0,
    position: "absolute",
  },
  predictiveInsights: {
    fontSize: FontSize.size_11xl,
    width: 295,
    textAlign: "left",
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    color: Color.bl,
  },
  groupChild: {
    backgroundColor: Color.mediumslateblue_100,
    left: 0,
    top: 0,
    opacity: 0.1,
    height: 97,
    width: 302,
    padding: 10,
  },
  ic24Bolt1Icon: {
    width: 16,
    height: 16,
    overflow: "hidden",
  },
  inactivity: {
    color: Color.mediumslateblue_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  ic24Bolt1Parent: {
    left: 35,
    width: 207,
    flexDirection: "column",
  },
  text: {
    left: 1,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  min: {
    color: Color.cornflowerblue_100,
    fontSize: FontSize.size_base,
    textAlign: "left",
  },
  parent: {
    top: 44,
    width: 109,
  },
  updatedToday: {
    fontSize: FontSize.size_lg,
    opacity: 0.7,
    left: 1,
    textAlign: "left",
  },
  groupItem: {
    backgroundColor: Color.palevioletred_100,
    opacity: 0.1,
    borderRadius: Border.br_xl,
  },
  factorOfRisk: {
    marginLeft: 7,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  ic24Heart1Parent: {
    width: 194,
  },
  typeI: {
    left: 2,
    width: 105,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    top: 0,
    position: "absolute",
  },
  min1: {
    width: 71,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    left: 0,
    position: "absolute",
  },
  typeIParent: {
    top: 42,
    width: 107,
  },
  groupInner: {
    backgroundColor: Color.lightseagreen_100,
    opacity: 0.1,
    borderRadius: Border.br_xl,
  },
  walking: {
    marginLeft: 7,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  ic24Flag1Parent: {
    width: 183,
  },
  text1: {
    width: 96,
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    top: 0,
    position: "absolute",
    left: 3,
  },
  km: {
    width: 60,
    fontFamily: FontFamily.dMSansRegular,
    top: 21,
    color: Color.lightseagreen_100,
    left: 0,
    position: "absolute",
  },
  group: {
    top: 39,
    width: 99,
  },
  groupParent: {
    top: 47,
    height: 199,
    left: 0,
    width: 306,
    position: "absolute",
  },
  updatedTodayParent: {
    top: 7,
    height: 246,
    left: 0,
    width: 306,
    position: "absolute",
  },
  syncIcon: {
    top: 10,
    left: 276,
    width: 20,
    height: 20,
    opacity: 0.5,
    overflow: "hidden",
    position: "absolute",
  },
  saturdayChild: {
    borderRadius: Border.br_xl,
    height: 40,
    width: 104,
    left: 0,
  },
  viewMore: {
    top: 11,
    left: 17,
    fontSize: FontSize.textSmallTextSemiBold_size,
    letterSpacing: 0.2,
    textTransform: "capitalize",
    fontWeight: "600",
    fontFamily: FontFamily.montserratSemibold,
    color: Color.whiteColor,
    textAlign: "left",
    position: "absolute",
  },
  saturday: {
    left: 153,
  },
  frameGroup: {
    height: 441,
    marginTop: 22,
    width: 306,
  },
  frameParent: {
    top: 106,
    left: 30,
    justifyContent: "center",
    alignItems: "center",
    position: "absolute",
  },
  disease: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    width: "100%",
    height: 812,
  },
});

export default Disease;
