import Home from "Home"
import Navbar from "Navbar"
import Orders from "Orders"
import { BrowserRouter, Routes, Route } from "react-router-dom"


function PageRoutes() {
    return (
        <BrowserRouter>
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/">
                    <Route path="orders" element={<Orders />} />
                </Route>
            </Routes>
        </BrowserRouter>
    )
}

export default PageRoutes