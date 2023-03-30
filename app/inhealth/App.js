import React, { useState, useEffect } from 'react';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { Accelerometer } from 'expo-sensors';
import { Gyroscope } from 'expo-sensors';

export default function App() {
  const [{ x, y, z }, setData] = useState({
    x: 0,
    y: 0,
    z: 0,
  });
  const [ data, setDataG] = useState({
    x: 0,
    y: 0,
    z: 0,
  });
  const [subscription, setSubscription] = useState(null);
  const [subscriptionG, setSubscriptionG] = useState(null);

  const _slow = () => {Accelerometer.setUpdateInterval(1000); Gyroscope.setUpdateInterval(1000)}
  const _fast = () => {Accelerometer.setUpdateInterval(16); Gyroscope.setUpdateInterval(16)}

  const _subscribe = () => {
    setSubscription(
      Accelerometer.addListener(setData)
    );
  };

  const _subscribeG = () => {
    setSubscriptionG(
      Gyroscope.addListener(gyroscopeData  => {
        setDataG(gyroscopeData );
      })
    );
  };
  const _unsubscribe = () => {
    subscription && subscription.remove();
    setSubscription(null);
  };
  const _unsubscribeG = () => {
    subscriptionG && subscriptionG.remove();
    setSubscriptionG(null);
  };

  useEffect(() => {
    _subscribe();
    _subscribeG();
    return () => {_unsubscribe();_unsubscribeG();}
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.text}>Accelerometer: (in gs where 1g = 9.81 m/s^2)</Text>
      <Text style={styles.text}>x: {x}</Text>
      <Text style={styles.text}>y: {y}</Text>
      <Text style={styles.text}>z: {z}</Text>
      <Text style={styles.text}>Gyroscope: </Text>
      <Text style={styles.text}>x: {data.x}</Text>
      <Text style={styles.text}>y: {data.y}</Text>
      <Text style={styles.text}>z: {data.z}</Text>
      <View style={styles.buttonContainer}>
        <TouchableOpacity onPress={subscription ?()=>{ _unsubscribe(); }: ()=>{_subscribe();}} style={styles.button}>
          <Text>{subscription ? 'On' : 'Off'}</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={subscriptionG ?()=>{  _unsubscribeG(); }: ()=>{ _subscribeG();}} style={styles.button}>
          <Text>{subscriptionG ? 'On' : 'Off'}</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={_slow} style={[styles.button, styles.middleButton]}>
          <Text>Slow</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={_fast} style={styles.button}>
          <Text>Fast</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  headline: {
    fontSize: 30,
    textAlign: 'center',
    margin: 10,
  },
  valueContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  valueValue: {
    width: 200,
    fontSize: 20
  },
  valueName: {
    width: 50,
    fontSize: 20,
    fontWeight: 'bold'
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});