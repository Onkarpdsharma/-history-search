import { BrowserRouter as Router, Routes, Route, NavLink } from "react-router-dom";

const Home = () => <div>Welcome to Home!</div>;
const About = () => <div>About Us Section</div>;
const Contact = () => <div>Contact Information</div>;

const App = () => {
    return (
        <Router>
            <div className="p-4">
                {/* Navigation Tabs */}
                <nav className="flex space-x-4 border-b pb-2">
                    <NavLink to="/" className={({ isActive }) => (isActive ? "font-bold border-b-2 border-blue-500" : "")}>Home</NavLink>
                    <NavLink to="/about" className={({ isActive }) => (isActive ? "font-bold border-b-2 border-blue-500" : "")}>About</NavLink>
                    <NavLink to="/contact" className={({ isActive }) => (isActive ? "font-bold border-b-2 border-blue-500" : "")}>Contact</NavLink>
                </nav>

                {/* Routes */}
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/about" element={<About />} />
                    <Route path="/contact" element={<Contact />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
