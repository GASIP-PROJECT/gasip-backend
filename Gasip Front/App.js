import { StyleSheet, Text, View } from 'react-native';
import { LogBox } from 'react-native';

import Layout from './Layout/layout';

LogBox.ignoreLogs(['Non-serializable values were found in the navigation state']); // Ignore log notification by message

export default function App() {
  return (
        <Layout/>
  );
};