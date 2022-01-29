import './App.css';
import { Route, Routes } from 'react-router-dom';
import GetAllUser from './Get/GetAllUser';
import AddUser from './Post/AddUser';
import Login from './Login/Login';

function App() {
  return (
    <>
    
    <Routes>
      <Route path="/" element={<AddUser></AddUser>}></Route>
      <Route path="/users" element={<GetAllUser></GetAllUser>}></Route>
      <Route path="/login" element={<Login></Login>}></Route>
    </Routes>
</>
  );
}

export default App;
