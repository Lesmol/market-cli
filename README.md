# MarketCLI

**MarketCLI** is a command-line interface (CLI) tool designed to interact with financial market data seamlessly from your terminal. It allows users to fetch stock fundamentals, perform valuation analysis (DCF), track earnings, and monitor forex rates using the Polygon.io API.

## 🚀 Features

* **Stock Analysis**: Retrieve financial ratios and fundamental data for specific tickers.
* **Valuation Tools**: Perform Discounted Cash Flow (DCF) calculations.
* **Earnings Data**: Access latest earnings results.
* **Forex Monitoring**: Check exchange rates and currency returns.
* **Persistent Configuration**: Securely stores your API key locally for subsequent use.

## 📋 Prerequisites

* **Polygon.io API Key**: You will need a valid API key from [Polygon.io](https://polygon.io/) to fetch market data.

## 💻 Usage

Once installed, you can access the tool using the `market` command.

### Stock Data
Retrieve financial ratios and data for a specific stock ticker using the `-t` (or `--ticker`) flag.

```
market stock -t AAPL
```
**Available Subcommands:**
* `return`: View YTD, 6-month, and 1-month returns.
* `earnings`: View latest earnings results.
* `dcf`: Perform Discounted Cash Flow calculations.

### Forex Data
Access forex exchange rates relative to the US Dollar for a specific currency using the `-c` (or `--currency`) flag.

```
market forex -c ZAR
```
**Available Subcommands:**
* `return`: View forex returns over specific periods.

## 📂 Project Structure
* `com.lvmp.cli`: Contains the Picocli command definitions (`RootCommand`, `Stock`, `Forex`).
* `com.lvmp.polygon`: Handles interaction with the data provider service.
* `com.lvmp.persistance`: Manages local file storage for API keys.
* `com.lvmp.config`: Defines application configuration paths (e.g., `.market` directory).

### Tools used
[![My Skills](https://skillicons.dev/icons?i=java,spring,git&perline=6)](https://skillicons.dev)
