import React, { useState } from "react";
import { Text, StyleSheet, Pressable, View, TextInput } from "react-native";
import { Image } from "expo-image";
import DropDownPicker from "react-native-dropdown-picker";
import { Datepicker as RNKDatepicker } from "@ui-kitten/components";
import { useNavigation } from "@react-navigation/native";
import { FontSize, Color, FontFamily } from "../GlobalStyles";

const Data = () => {
  const [rectangleDropdownOpen, setRectangleDropdownOpen] = useState(false);
  const [rectangleDropdownValue, setRectangleDropdownValue] = useState("");
  const [rectangleDropdownItems, setRectangleDropdownItems] = useState([
    { value: "Afghanistan", label: "Afghanistan" },
    { value: "Albania", label: "Albania" },
    { value: "Algeria", label: "Algeria" },
    { value: "Andorra", label: "Andorra" },
    { value: "Angola", label: "Angola" },
    { value: "Antigua and Barbuda", label: "Antigua and Barbuda" },
    { value: "Argentina", label: "Argentina" },
    { value: "Armenia", label: "Armenia" },
    { value: "Australia", label: "Australia" },
    { value: "Austria", label: "Austria" },
    { value: "Azerbaijan", label: "Azerbaijan" },
    { value: "Bahamas", label: "Bahamas" },
    { value: "Bahrain", label: "Bahrain" },
    { value: "Bangladesh", label: "Bangladesh" },
    { value: "Barbados", label: "Barbados" },
    { value: "Belarus", label: "Belarus" },
    { value: "Belgium", label: "Belgium" },
    { value: "Belize", label: "Belize" },
    { value: "Benin", label: "Benin" },
    { value: "Bhutan", label: "Bhutan" },
    { value: "Bolivia", label: "Bolivia" },
    { value: "Bosnia and Herzegovina", label: "Bosnia and Herzegovina" },
    { value: "Botswana", label: "Botswana" },
    { value: "Brazil", label: "Brazil" },
    { value: "Brunei", label: "Brunei" },
    { value: "Bulgaria", label: "Bulgaria" },
    { value: "Burkina Faso", label: "Burkina Faso" },
    { value: "Burundi", label: "Burundi" },
    { value: "Cabo Verde", label: "Cabo Verde" },
    { value: "Cambodia", label: "Cambodia" },
    { value: "Cameroon", label: "Cameroon" },
    { value: "Canada", label: "Canada" },
    { value: "Central African Republic", label: "Central African Republic" },
    { value: "Chad", label: "Chad" },
    { value: "Chile", label: "Chile" },
    { value: "China", label: "China" },
    { value: "Colombia", label: "Colombia" },
    { value: "Comoros", label: "Comoros" },
    { value: "Congo", label: "Congo" },
    { value: "Costa Rica", label: "Costa Rica" },
    { value: "Croatia", label: "Croatia" },
    { value: "Cuba", label: "Cuba" },
    { value: "Cyprus", label: "Cyprus" },
    { value: "Czech Republic", label: "Czech Republic" },
    { value: "Denmark", label: "Denmark" },
    { value: "Djibouti", label: "Djibouti" },
    { value: "Dominica", label: "Dominica" },
    { value: "Dominican Republic", label: "Dominican Republic" },
    { value: "East Timor", label: "East Timor" },
    { value: "Ecuador", label: "Ecuador" },
    { value: "Egypt", label: "Egypt" },
    { value: "El Salvador", label: "El Salvador" },
    { value: "Equatorial Guinea", label: "Equatorial Guinea" },
    { value: "Eritrea", label: "Eritrea" },
    { value: "Estonia", label: "Estonia" },
    { value: "Eswatini", label: "Eswatini" },
    { value: "Ethiopia", label: "Ethiopia" },
    { value: "Fiji", label: "Fiji" },
    { value: "Finland", label: "Finland" },
    { value: "France", label: "France" },
    { value: "Gabon", label: "Gabon" },
    { value: "Gambia", label: "Gambia" },
    { value: "Georgia", label: "Georgia" },
    { value: "Germany", label: "Germany" },
    { value: "Ghana", label: "Ghana" },
    { value: "Greece", label: "Greece" },
    { value: "Grenada", label: "Grenada" },
    { value: "Guatemala", label: "Guatemala" },
    { value: "Guinea", label: "Guinea" },
    { value: "Guinea-Bissau", label: "Guinea-Bissau" },
    { value: "Guyana", label: "Guyana" },
    { value: "Haiti", label: "Haiti" },
    { value: "Honduras", label: "Honduras" },
    { value: "Hungary", label: "Hungary" },
    { value: "Iceland", label: "Iceland" },
    { value: "India", label: "India" },
    { value: "Indonesia", label: "Indonesia" },
    { value: "Iran", label: "Iran" },
    { value: "Iraq", label: "Iraq" },
    { value: "Ireland", label: "Ireland" },
    { value: "Israel", label: "Israel" },
    { value: "Italy", label: "Italy" },
    { value: "Jamaica", label: "Jamaica" },
    { value: "Japan", label: "Japan" },
    { value: "Jordan", label: "Jordan" },
    { value: "Kazakhstan", label: "Kazakhstan" },
    { value: "Kenya", label: "Kenya" },
    { value: "Kiribati", label: "Kiribati" },
    { value: "Korea", label: "Korea" },
    { value: "North Korea", label: "North Korea" },
    { value: "South Kuwait", label: "South Kuwait" },
    { value: "Kyrgyzstan", label: "Kyrgyzstan" },
    { value: "Laos", label: "Laos" },
    { value: "Latvia", label: "Latvia" },
    { value: "Lebanon", label: "Lebanon" },
    { value: "Lesotho", label: "Lesotho" },
    { value: "Liberia", label: "Liberia" },
    { value: "Libya", label: "Libya" },
    { value: "Liechtenstein", label: "Liechtenstein" },
    { value: "Lithuania", label: "Lithuania" },
    { value: "Luxembourg", label: "Luxembourg" },
    { value: "Madagascar", label: "Madagascar" },
    { value: "Malawi", label: "Malawi" },
    { value: "Malaysia", label: "Malaysia" },
    { value: "Maldives", label: "Maldives" },
    { value: "Mali", label: "Mali" },
    { value: "Malta", label: "Malta" },
    { value: "Marshall Islands", label: "Marshall Islands" },
    { value: "Mauritania", label: "Mauritania" },
    { value: "Mauritius", label: "Mauritius" },
    { value: "Mexico", label: "Mexico" },
    { value: "Micronesia", label: "Micronesia" },
    { value: "Moldova", label: "Moldova" },
    { value: "Monaco", label: "Monaco" },
    { value: "Mongolia", label: "Mongolia" },
    { value: "Montenegro", label: "Montenegro" },
    { value: "Morocco", label: "Morocco" },
    { value: "Mozambique", label: "Mozambique" },
    { value: "Myanmar", label: "Myanmar" },
    { value: "Namibia", label: "Namibia" },
    { value: "Nauru", label: "Nauru" },
    { value: "Nepal", label: "Nepal" },
    { value: "Netherlands", label: "Netherlands" },
    { value: "New Zealand", label: "New Zealand" },
    { value: "Nicaragua", label: "Nicaragua" },
    { value: "Niger", label: "Niger" },
    { value: "Nigeria", label: "Nigeria" },
    { value: "North Macedonia", label: "North Macedonia" },
    { value: "Norway", label: "Norway" },
    { value: "Oman", label: "Oman" },
    { value: "Pakistan", label: "Pakistan" },
    { value: "Palau", label: "Palau" },
    { value: "Panama", label: "Panama" },
    { value: "Papua New Guinea", label: "Papua New Guinea" },
    { value: "Paraguay", label: "Paraguay" },
    { value: "Peru", label: "Peru" },
    { value: "Philippines", label: "Philippines" },
    { value: "Poland", label: "Poland" },
    { value: "Portugal", label: "Portugal" },
    { value: "Qatar", label: "Qatar" },
    { value: "Romania", label: "Romania" },
    { value: "Russia", label: "Russia" },
    { value: "Rwanda", label: "Rwanda" },
    { value: "Saint Kitts and Nevis", label: "Saint Kitts and Nevis" },
    { value: "Saint Lucia", label: "Saint Lucia" },
    {
      value: "Saint Vincent and the Grenadines",
      label: "Saint Vincent and the Grenadines",
    },
    { value: "Samoa", label: "Samoa" },
    { value: "San Marino", label: "San Marino" },
    { value: "Sao Tome and Principe", label: "Sao Tome and Principe" },
    { value: "Saudi Arabia", label: "Saudi Arabia" },
    { value: "Senegal", label: "Senegal" },
    { value: "Serbia", label: "Serbia" },
    { value: "Seychelles", label: "Seychelles" },
    { value: "Sierra Leone", label: "Sierra Leone" },
    { value: "Singapore", label: "Singapore" },
    { value: "Slovakia", label: "Slovakia" },
    { value: "Slovenia", label: "Slovenia" },
    { value: "Solomon Islands", label: "Solomon Islands" },
    { value: "Somalia", label: "Somalia" },
    { value: "South Africa", label: "South Africa" },
    { value: "South Sudan", label: "South Sudan" },
    { value: "Spain", label: "Spain" },
    { value: "Sri Lanka", label: "Sri Lanka" },
    { value: "Sudan", label: "Sudan" },
    { value: "Suriname", label: "Suriname" },
    { value: "Sweden", label: "Sweden" },
    { value: "Switzerland", label: "Switzerland" },
    { value: "Syria", label: "Syria" },
    { value: "Taiwan", label: "Taiwan" },
    { value: "Tajikistan", label: "Tajikistan" },
    { value: "Tanzania", label: "Tanzania" },
    { value: "Thailand", label: "Thailand" },
    { value: "Togo", label: "Togo" },
    { value: "Tonga", label: "Tonga" },
    { value: "Trinidad and Tobago", label: "Trinidad and Tobago" },
    { value: "Tunisia", label: "Tunisia" },
    { value: "Turkey", label: "Turkey" },
    { value: "Turkmenistan", label: "Turkmenistan" },
    { value: "Tuvalu", label: "Tuvalu" },
    { value: "Uganda", label: "Uganda" },
    { value: "Ukraine", label: "Ukraine" },
    { value: "United Arab Emirates", label: "United Arab Emirates" },
    { value: "United Kingdom", label: "United Kingdom" },
    { value: "United States", label: "United States" },
    { value: "Uruguay", label: "Uruguay" },
    { value: "Uzbekistan", label: "Uzbekistan" },
    { value: "Vanuatu", label: "Vanuatu" },
    { value: "Vatican City", label: "Vatican City" },
    { value: "Venezuela", label: "Venezuela" },
    { value: "Vietnam", label: "Vietnam" },
    { value: "Yemen", label: "Yemen" },
    { value: "Zambia", label: "Zambia" },
    { value: "Zimbabwe", label: "Zimbabwe" },
  ]);
  const [rectangleDatePicker, setRectangleDatePicker] = useState(undefined);
  const navigation = useNavigation();

  return (
    <View style={styles.data}>
      <View style={styles.frameParent}>
        <View style={styles.giveUsSomeBasicInformationParent}>
          <Text style={styles.giveUsSome}>Give us some basic information</Text>
          <View style={styles.frameGroup}>
            <View style={styles.groupParent}>
              <Pressable style={styles.parentLayout}>
                <Text style={[styles.male, styles.maleTypo]}>Male</Text>
                <Image
                  style={[styles.groupChild, styles.namePosition]}
                  contentFit="cover"
                  source={require("../assets/group-11.png")}
                />
              </Pressable>
              <Pressable style={[styles.femaleParent, styles.parentLayout]}>
                <Text style={[styles.female, styles.maleTypo]}>Female</Text>
                <Image
                  style={[styles.groupChild, styles.namePosition]}
                  contentFit="cover"
                  source={require("../assets/group-9.png")}
                />
              </Pressable>
            </View>
            <View style={[styles.nameWrapper, styles.wrapperLayout1]}>
              <Text style={[styles.name, styles.nameTypo]}>Name</Text>
            </View>
            <TextInput
              style={[styles.frameChild, styles.wrapperLayout]}
              placeholder="Placeholder text"
              keyboardType="default"
            />
            <View style={[styles.heightWrapper, styles.wrapperLayout1]}>
              <Text style={[styles.name, styles.nameTypo]}>Height</Text>
            </View>
            <TextInput
              style={[styles.frameChild, styles.wrapperLayout]}
              placeholder="Placeholder text"
              keyboardType="decimal-pad"
              autoCapitalize="none"
            />
            <View style={[styles.weightWrapper, styles.wrapperLayout1]}>
              <Text style={[styles.name, styles.nameTypo]}>Weight</Text>
            </View>
            <TextInput
              style={[styles.frameChild, styles.wrapperLayout]}
              placeholder="Placeholder text"
              keyboardType="decimal-pad"
              autoCapitalize="none"
            />
            <View style={[styles.countryWrapper, styles.wrapperLayout1]}>
              <Text style={[styles.name, styles.nameTypo]}>Country</Text>
            </View>
            <View style={styles.wrapperLayout}>
              <DropDownPicker
                style={styles.dropdownpicker}
                open={rectangleDropdownOpen}
                setOpen={setRectangleDropdownOpen}
                value={rectangleDropdownValue}
                setValue={setRectangleDropdownValue}
                items={rectangleDropdownItems}
                dropDownContainerStyle={
                  styles.rectangleDropdowndropDownContainer
                }
              />
            </View>
            <Text style={[styles.birthday, styles.nameTypo]}>Birthday</Text>
            <RNKDatepicker
              style={styles.rectangleRnkdatepicker}
              date={rectangleDatePicker}
              onSelect={setRectangleDatePicker}
              controlStyle={styles.rectangleDatePickerValue}
            />
            <Text style={[styles.medicalHistory, styles.nameTypo]}>
              Medical History
            </Text>
            <TextInput
              style={[styles.frameChild, styles.wrapperLayout]}
              placeholder="Placeholder text"
              keyboardType="default"
            />
          </View>
        </View>
        <Pressable
          style={styles.container}
          onPress={() => navigation.navigate("Home")}
        >
          <Image
            style={styles.icon}
            contentFit="cover"
            source={require("../assets/group-10.png")}
          />
        </Pressable>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  rectangleDropdowndropDownContainer: {
    backgroundColor: "#d9d9d9",
  },
  rectangleDatePickerValue: {
    position: "relative",
    width: 297,
    height: 28,
    backgroundColor: "#d9d9d9",
  },
  maleTypo: {
    fontSize: FontSize.textMediumTextSemiBold_size,
    top: 140,
    textAlign: "center",
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
    position: "absolute",
  },
  namePosition: {
    left: 0,
    top: 0,
    position: "absolute",
  },
  parentLayout: {
    height: 158,
    width: 132,
  },
  wrapperLayout1: {
    height: 26,
    marginTop: 7,
  },
  nameTypo: {
    fontFamily: FontFamily.dMSansBold,
    fontWeight: "700",
    fontSize: FontSize.size_xl,
    color: Color.bl,
  },
  wrapperLayout: {
    height: 28,
    width: 297,
    marginTop: 7,
  },
  giveUsSome: {
    fontSize: 28,
    width: 295,
    textAlign: "left",
    color: Color.bl,
    fontFamily: FontFamily.dMSansMedium,
    fontWeight: "500",
  },
  male: {
    left: 50,
    textAlign: "center",
  },
  groupChild: {
    height: 132,
    width: 132,
    top: 0,
  },
  female: {
    left: 42,
    textAlign: "center",
  },
  femaleParent: {
    marginLeft: 31,
  },
  groupParent: {
    flexDirection: "row",
    alignItems: "center",
  },
  name: {
    left: 0,
    top: 0,
    position: "absolute",
    textAlign: "center",
  },
  nameWrapper: {
    width: 57,
    marginTop: 7,
  },
  frameChild: {
    backgroundColor: Color.gainsboro,
  },
  heightWrapper: {
    width: 64,
    marginTop: 7,
  },
  weightWrapper: {
    width: 69,
    marginTop: 7,
  },
  countryWrapper: {
    width: 80,
    marginTop: 7,
  },
  dropdownpicker: {
    backgroundColor: Color.gainsboro,
  },
  birthday: {
    marginTop: 7,
    textAlign: "center",
  },
  rectangleRnkdatepicker: {
    marginTop: 7,
  },
  medicalHistory: {
    width: 222,
    marginTop: 7,
    textAlign: "left",
  },
  frameGroup: {
    marginTop: 20,
  },
  giveUsSomeBasicInformationParent: {
    alignItems: "center",
  },
  icon: {
    height: "100%",
    width: "100%",
  },
  container: {
    width: 56,
    height: 56,
    marginTop: 8,
  },
  frameParent: {
    top: 51,
    left: 38,
    alignItems: "flex-end",
    justifyContent: "center",
    position: "absolute",
  },
  data: {
    backgroundColor: Color.whiteColor,
    flex: 1,
    height: 812,
    width: "100%",
  },
});

export default Data;
