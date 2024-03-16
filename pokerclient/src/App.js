import { Routes, Route, BrowserRouter } from "react-router-dom";


import { Header } from "./components/Header";


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header/>
        <Routes>
          {/* <Route path="/" element={}/> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
