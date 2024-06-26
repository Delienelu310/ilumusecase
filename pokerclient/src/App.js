import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css'

import { Routes, Route, BrowserRouter } from "react-router-dom";
import AuthProvider from "./authentication/AuthContext";

import { Header } from "./components/Header";

import TableListPage from "./pages/TableListPage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import TablePage from "./pages/TablePage";
import CreateTablePage from './pages/CreateTablePage';

function App() {

  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <Header/>
          <Routes>
            <Route path="/" element={<TableListPage/>}/>
            <Route path="/register" element={<RegisterPage/>}/>
            <Route path="/login" element={<LoginPage/>}/>
            <Route path="/tables/:tableId" element={<TablePage/>}/>
            <Route path="/tables/create" element={<CreateTablePage/>}/>
          </Routes>
        </BrowserRouter>
      </AuthProvider>
      
    </div>
  );
}

export default App;
