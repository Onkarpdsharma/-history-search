import React, { useState } from "react";
import axios from "axios";

const BitcoinPriceChecker = () => {
    const [start, setStart] = useState("");
    const [end, setEnd] = useState("");
    const [currency, setCurrency] = useState("USD");
    const [data, setData] = useState(null);
    const [error, setError] = useState(null);

    const fetchPrices = async () => {
        setError(null);
        try {
            const response = await axios.get("http://localhost:8080/api/bitcoin/price", {
                params: { start, end, currency },
            });
            setData(response.data);
        } catch (err) {
            setError("Failed to fetch data. Try again.");
        }
    };

    return (
        <div style={{ padding: "20px", maxWidth: "500px", margin: "auto" }}>
            <h2>Bitcoin Price Checker</h2>
            <label>Start Date:</label>
            <input type="date" value={start} onChange={(e) => setStart(e.target.value)} />
            <br />
            <label>End Date:</label>
            <input type="date" value={end} onChange={(e) => setEnd(e.target.value)} />
            <br />
            <label>Currency11:</label>
            <select value={currency} onChange={(e) => setCurrency(e.target.value)}>
                <option value="USD">USD</option>
                <option value="EUR">EUR</option>
                <option value="GBP">GBP</option>
                <option value="INR">INR</option>
            </select>
            <br />
            <button onClick={fetchPrices}>Get Prices</button>
            {error && <p style={{ color: "red" }}>{error}</p>}
            {data && (
                <div>
                    <h3>Results:</h3>
                    <pre>{JSON.stringify(data, null, 2)}</pre>
                </div>
            )}
        </div>
    );
};

export default BitcoinPriceChecker;
