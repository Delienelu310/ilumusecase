import { Routes, Route, BrowserRouter } from "react-router-dom";
import AuthProvider from "./authentication/AuthContext";

import { Header } from "./components/Header";
import TableListPage from "./pages/TableListPage";


function App() {
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <Header/>
          <Routes>
            <Route path="/" element={<TableListPage/>}/>
          </Routes>
        </BrowserRouter>
      </AuthProvider>
      
    </div>
  );
}

export default App;
