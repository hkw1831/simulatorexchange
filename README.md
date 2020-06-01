# Simulator Exchange

This project contains a fake simulator exchange for order and trades.

It supports new / cancel / replace / trade function.

## How to start the simulator

To start the simulator, follow the following steps:
1. Run `./gradlew clean build` to build the project
2. Place the built jar from `build/libs` to a directory and run `java -cp simulatorexchange-1.0.0.jar simulator.Simulator`

## Specification of this simulator exchange

### 1. Connectivity

Exchange participants connect their trading system to this simulator via a standard TCP/IP point-to-point connection.

#### 1.1 IP Address and Port Numbers

User that wishes to connect to this simulator exchange will need to connect to exchange's provided IP and Port. While IP is predefined, port is default to 8888 if not specified.

#### 1.2 Username and Password

User must specify the valid username and password fields of the logon message.

#### 1.3 Message Sequence Numbers

User and this simulator will each maintain a separate and independent set of incoming and outgoing Message Sequence Numbers. Message Sequence Numbers should be initialized to 0 each day and be incremented throughout the session.


#### 1.4 Failure and Recovery

This simulator has not been designed with fault tolerant and disaster recovery technology that ensures that trading should continue in the likely event of a process or server outage.

### 2. Data Types

|Data Type|Size in Bytes|Description|
|---|---|---|
|String|32|Standard ASCII character bytes. In case the field is empty, the first byte is null filled|
|Int|2|unsigned Integer (0-65535)|

### 3.Messages

All messages comprises of the following components:

* Header
* Body

All fields in messages are fixed length which values are stored as Big endian.

#### 3.1 Header

|Field Name|Type|Required|Description|
|---|---|---|---|
|Length|Int|Y||
|Message Type|String|Y||
|Sequence Number|Int|Y||
|Username|String|N||

#### 3.2 Session Messages

#### 3.2.1 Logon

This message is initiated by the user and UL may respond with the same message.

|Field Name|Type|Required|Description|
|---|---|---|---|
|Username|String|Y||
|Password|String|Y||
|Next Sequence Number|Y||

#### 3.2.2 Logout

|Field Name|Type|Required|Description|
|---|---|---|---|
||||

#### 3.3 Business Messages

##### 3.3.1 New Order

|Field Name|Type|Required|Description|
|---|---|---|---|
|Client Order Id|String|YUnique identifier of the order|
|Instrument Id|String|Y||
|Order Type|String|Y|limit or market|
|Side|String|Y|buy or sell|
|Order Quantity|Int|Y||
|Price|Int|N||
|Time in force|String|N|day, fak or fok|
|Transaction Time|String|Y|Format: YYYYMMDD-HH:mm:SS.sss|

##### 3.3.2 Replace Order

|Field Name|Type|Required|Description|
|---|---|---|---|
|Client Order Id|String|Y|Unique identifier of the order|
|Original Client Order Id|String|Y|Client Order Id of the order being replaced|
|Order Quantity|Int|Y|Quantity cannot be reduced|
|Price|Int|N||

##### 3.3.3 Cancel Order

|Field Name|Type|Required|Description|
|---|---|---|---|
|Client Order Id|String|Y|Unique identifier of the order|
|Original Client Order Id|String|Y|Client Order Id of the order being cancelled|

##### 3.3.4 Execution Report

|Field Name|Type|Required|Description|
|---|---|---|---|
|Client Order Id|String|Y|Unique identifier of the order|
|Original Client Order Id|String|Y|Client Order Id of the order being replaced / cancelled|
|Instrument Id|String|Y||
|Side|String|Y|buy or sell|
|Order Id|String|Y||
|Order Type|String|Y|limit or market|
|Order Quantity|Int|Y||
|Price|Int|N||
|Time in force|String|N|day, fak or fok|
|Exec Type|String|Y|new, replace, cancel, reject or trade|
|Cumulative Quantity|Int|N||
|Leaves Quantity|Int|N||
|Last Price|Int|N||
|Last Quantity|Int|N||
|Reject Code|Int|N|90=Invalid Msg Type|
| | | |91=Order Not Found|
| | | |92=Invalid Quantity|
| | | |93=Invalid Side|
| | | |94=Invalid Order Type|
| | | |95=Invalid Price|
| | | |96=Missing Price for Limit Order|
| | | |97=Invalid Client Order Id|
| | | |98=Invalid Time In Force|
| | | |99=Other|