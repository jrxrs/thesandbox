# Money Week Videos #

http://www.moneyweek.com offers a fantastic range of introductory videos available on their [youtube channel](http://www.youtube.com/user/MoneyWeekVideos). Links to specific videos are scattered below.

# Financial Glossary #




---


## General Terms & Definitions ##

### Algorithmic (Algo) Trading ###

  * [Part 1 of Compare Brokers' Introduction to Algorithmic Trading](http://www.comparebroker.com/expertspeak/intro-to-algorithmic-trading-part-i/)
  * [Part 2 of Compare Brokers' Introduction to Algorithmic Trading](http://www.comparebroker.com/expertspeak/intro-to-algorithmic-trading-part-ii/)
  * [Type of Algo Trading](http://www.comparebroker.com/expertspeak/popular-algorithmic-trading-strategies-part-i/) - Covers VWAP, TWAP & POV.

#### Percentage of Volume (POV) ####

We saw that trying to predict VWAP comes with its own set of problems. Predicting volume curve can be tricky. Another option to achieving a close to VWAP price is to use a [heuristic](http://en.wikipedia.org/wiki/Heuristic_%28computer_science%29). If you could make a good guess on what is the percentage required to execute the order, you should be able to achieve a near VWAP price.

So, the algorithm starts by taking a percentage as input. And it keeps calculating the real-time volume for that security. It then matches the given percentage of the volume for each interval. Let’s say you gave 10% as input. And in the last interval overall volume for the security was 5000. POV will then place an order of size = 500.

**Early finish and unexecuted orders** <br>
The problem with POV is that with POV you might actually finish the order in advance or may not be able to finish the order at all, in allotted time.  This requires that the algorithm needs to have a catch-up logic. In case, it’s falling behind its increases the participation rate or the percentage.  In the last few minutes, you might just abandon the original logic and just execute the remaining quantity as a TWAP order.<br>
<br>
<h4>Time Weighted Average Price (TWAP)</h4>

TWAP simply breaks the overall order into small chunks of equal size, usually, and executes them each of them after a fixed interval. For as long as all the orders are getting executed, the overall price would be the Time weighted average of the prices for which the algorithm ran.<br>
<br>
<b>Randomization</b>

Running a simple TWAP has one problem.  Anyone observing the order book can figure out that there is a consistent pattern.  These could be done manually as well as by other algorithms. So, most versions of TWAPs would involve some randomization. Either we do it in terms of sizes of orders or in the time interval between the slices or both.<br>
<br>
<h4>Volume Weighted Average Price (VWAP)</h4>

This algorithm basically attempts to match the VWAP (Volume Weighted Average Price) over a defined time period. Suppose I place an order at 10:00 am and run it for 1 hour and say the size was 10,000. In this period, overall volume was 100,000. So, if I go and check every five minute, the algorithm should have matched 10% of the overall volume for those five minutes. If your order size was 20,000, your participation rate will be 20% and so on.<br>
<br>
<b>There's a BUT!</b> How do you know what the volume is going to be during the time your order will be executing? You can't, so you need to attempt to accurately model it using a <i>Volume Profile</i>. So, by now it's clear that we need to give the algorithm a volume profile for the time period it will be active. A simple version might just take the volume of the last day and assume that volume will be more or less the same.  A more sophisticated version would take average over a big period to take out noise.  A still more sophisticated version would actually use high-frequency trading models to predict the volume profile for the day.<br>
<br>
<a href='http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:vwap_intraday'>VWAP</a>

VWAP is calculated by multiplying the volume at each price level by the respective price and dividing by the total volume. The more volume traded at a certain price level, the more impact that price has on VWAP.<br>
<br>
<blockquote>VWAP = ∑(Pn*Vn) / ∑(Vn)<br>
where P = price traded<br>
V = volume traded<br>
n = number of trades</blockquote>

As an example, consider the following series of trades:<br>
<ul><li>500 shares @ $10.00<br>
</li><li>300 shares @ $10.05<br>
</li><li>200 shares @ $10.10<br>
The average price for these three trades is $10.05, however the VWAP is $10.035 because more volume was executed at the $10.00 level than at the $10.10 price. One of the keys to a successful VWAP trade is anticipating market volume and participating accordingly.</li></ul>

<h4>Others</h4>

<ul><li><a href='http://www.comparebroker.com/expertspeak/popular-algorithmic-trading-strategies-part-ii/'>Display Size, Implementation Shortfall &amp; Liquidity Seeker Explained</a>
</li><li><a href='https://www.thetradestreet.com/blogs/96/8/popular-algorithmic-trading-stra'>Liquidity Seekers: Dark Pools, Guerilla, Sniper &amp; Sniffer Explained</a></li></ul>

<h3>Alpha</h3>

<a href='http://www.youtube.com/watch?v=2Kz4nKYvF14'>What is Alpha? - MoneyWeek Investment Tutorials</a>

Alpha (aka the alpha coefficient) is a way of analysing the value that an active fund manager, i.e. someone who tries to beat their benchmark rather than just track it – adds to his or her fund.<br>
<br>
Alpha measures the rate of return made by a portfolio relative to the return on that benchmark after adjusting for any added investment risk (which is measured by beta – see below). For example, suppose a fund achieves a 30% return while its benchmark rises by 8%. But say the fund is three times more volatile than the benchmark (in other words, it has a beta of three). That would make the fund's alpha 30% – (3 x 8%), i.e. 6%.<br>
<br>
This means the fund has returned 6% more than you would expect, once allowance has been made for the additional risk that the portfolio manager has taken on.<br>
<br>
<h3>Arbitrage</h3>

The simultaneous purchase and selling of a security in order to profit from a differential in the price. This usually takes place on different exchanges or marketplaces. Also known as a riskless profit. An example of this is when an arbitrageur buys a stock on a foreign exchange that hasn't adjusted for the constantly changing exchange rate. The arbitrageur will purchase the undervalued stock and short sell the overvalued stock, thus profiting from the difference. This is recommended for experienced investors only.<br>
<br>
<h3>Asset Management</h3>

1. The management of the financial assets of a company in order to maximize return.<br>
<br>
2. An account at a financial institution that includes checking services, credit cards, debit cards, margin loans, the automatic sweep of cash balances into a money market fund, as well as brokerage services.<br>
<br>
Quite often these are for high net-worth clients. They are convenient because the client gets a complete financial summary of banking and brokerage activity on one statement. Also known as an asset management account or a central asset account.<br>
<br>
<h3>Back Office</h3>

Administration and support personnel in a financial services company. They carry out functions like settlements, clearances, record maintenance, regulatory compliance, and accounting.<br>
<br>
<h3>Basket Trading</h3>

The buying or selling of a set of 15 or more securities in a single order. Used by institutional investors or program traders to invest large amounts of money in a particular portfolio or index.<br>
<br>
<h3>Beta</h3>

<a href='http://www.youtube.com/watch?v=etlv7qTQUSY'>What is beta? - MoneyWeek investment tutorials</a>

Beta (or beta coefficient) is a way to measure the relative riskiness of a share. It does so by comparing the historic movement in the share price to that of the market as a whole. So for example if a share moved up by 10% when the market rose by 50%, beta is 10/50 = 0.2. Equally a share that increased by 100% when the market rose 50% has a beta of 100/50 = 2. This is useful for predictive purposes as a share with a historic beta of say 0.8 will be expected to move 80% of the distance of the wider market in the future. Shares with a high beta value - say technology and software companies - are generally more volatile and therefore seen as higher risk than those with a low beta - such as utility companies. A diversified portfolio will aim to maintain a mixture of high and low beta stocks as a way of managing risk.<br>
<br>
<h3>Bid-Ask</h3>

Also known as bid-offer or buy-sell.<br>
<br>
Bid - What buyers are will to pay.<br>
Ask - What sellers want to get.<br>
<br>
The bid–offer spread (also known as bid–ask or buy–sell spread, and their equivalents using slashes in place of the dashes) for securities is the difference between the prices quoted (either by a single market maker or in a limit order book) for an immediate sale (offer) and an immediate purchase (bid).<br>
<br>
<h3>Bloomberg</h3>

Bloomberg is a major global provider of 24-hour financial news and information including real-time and historic price data, financials data, trading news and analyst coverage, as well as general news and sports. Its services, which span their own platform, television, radio and magazines, offer professionals analytic tools.<br>
<br>
<h4>Bloomberg Trading Solutions (BTS)</h4>

Also see TOMS below, which is one of the solutions Bloomberg offers to sell-side firms for order management.<br>
<br>
<h4>Trade Order Management Solutions (TOMS)</h4>

<a href='http://www.bloomberg.com/enterprise/trading_solutions/sell_side_oms/'>BTS / TOMS</a>

For sell-side fixed income firms, Bloomberg Trade Order Management Solutions (TOMS) delivers global, multi-asset solutions for front-end inventory, trading, and middle & back office operations. TOMS enables you to manage inventory, market making, intraday risk and compliance, P&L, and end-of-day close in a fully customizable workflow. With TOMS, you can optimize your workflow, increase global distribution to markets, manage risk and compliance, and improve operational efficiency.<br>
<br>
In this context the following activities fall under Inventory, Trading & Operations:<br>
<br>
<table><thead><th> <b>Inventory</b> </th><th> <b>Trading</b> </th><th> <b>Operations</b> </th></thead><tbody>
<tr><td> Pricing          </td><td> Market Making  </td><td> Middle Office     </td></tr>
<tr><td> Risk Management  </td><td> Order Management </td><td> STP / Integration </td></tr>
<tr><td> P&L Reporting    </td><td> Trade Capture  </td><td>                   </td></tr>
<tr><td>                  </td><td> Regulatory Reporting </td><td>                   </td></tr></tbody></table>

<h3>Buy-side vs. Sell-side</h3>

From <a href='http://www.vault.com/articles/The-buy-side-vs.-sell-side-15434841.html'>here</a>:<br>
<br>
Simply stated, the buy-side refers to the asset managers who represent individual and institutional investors. The buy-side purchases investment products with the goal of increasing its' assets. The sell-side refers to the functions of an investment bank. Specifically, this includes investment bankers, traders and research analysts. Sell-side professionals issue, recommend, trade and "sell" securities for the investors on the buy-side to "buy." The sell-side can be thought of primarily as a facilitator of buy-side investments --the sell-side makes money not through a growth in value of the investment, but through fees and commissions for these facilitating services. In this chapter, we'll take a brief look at the types of jobs on each "side." The rest of the book will look at the buy-side in detail.<br>
<br>
So in essence Goldman Sachs would be the sell-side, where as Blackrock or Fidelity would be the buy-side.<br>
<br>
<h3>Capital Markets</h3>

The market where capital, such as stocks and bonds, are traded. Capital markets are used by firms to raise additional funds.<br>
<br>
<h3>Cash Markets</h3>

The market for a cash commodity or actual, as opposed to its futures contract. A cash market may take forms such as the following: self-regulated centralized markets, like commodity exchanges, decentralized over-the-counter markets where private transactions may occur, or localised community organizations such as grain elevators. At these locations one can purchase the actual physical commodity rather than just the futures contract.<br>
<br>
<h3>Compliance</h3>

The department within a brokerage firm that oversees the trading and market-making activities of the firm. It ensures that the employees and officers of the firm are abiding by the rules and regulations of the Financial Services Authority.<br>
<br>
<h3>Corporate Finance</h3>

Any financial or monetary activity that deals with a company and its money. This can include anything from IPO’s to acquisitions.<br>
<br>
<h3>Delta One</h3>

Delta One desks focus on “delta hedging”, a very common investment-banking activity. Usually it refers to the way a bank hedges its long and short exposures across a portfolio of investments that may include assets such as shares, as well as derivatives such as futures and options.<br>
<br>
To make a portfolio ‘price neutral’, you need an overall ‘delta’ of zero. That means for every 5% your long positions gain (say stocks you own), your short positions (created using, for instance, short futures or put options) also lose 5% and vice versa, so your overall gain or loss is zero. In reality, perfect delta hedges are hard to achieve and maintain. In the UBS case, some trades may not have been delta hedged at all.<br>
<br>
<h3>Dividend Yield</h3>

Holders of ordinary shares in a company are paid a percentage of that company's profits every year. The payment is called a dividend. The percentage is not worked out using an exact formula, but decided by the board and approved by shareholders at the AGM, depending on the company's performance and priorities. The dividend per share (total dividends paid out divided by total number of shares) expressed as a percentage is referred to as the dividend yield. So if a share is trading at 100p, and the dividend paid out last year was 2p, the yield is 2%. This is a popular measure for comparing stocks, but note that it is retrospective - telling you what the company paid out last year, not what it might pay out next. A low yield often - but not always - suggests a fast-growing company not yet making much profit. Investors will accept a low yield now in exchange for an anticipated high yield in the future. A high yield often indicates either a very risky or slow-growing company; investors demand a higher yield as compensation to invest in either.<br>
<br>
<a href='http://www.youtube.com/watch?v=3WByrAfY39k'>What is dividend yield? - Money Week Investment Tutorials</a>

<h3>Earnings per Share (EPS)</h3>

<a href='http://www.youtube.com/watch?v=vksGv_7sdIA'>What does 'earnings per share' mean? - MoneyWeek Investment Tutorials</a>

Earnings per share is seen as one of the best means of determining a share's true price, as it shows how much of a firm's profits (after tax) each shareholder owns. It is calculated by dividing a company's net earnings by the number of shares issued, and is most often used as a means of comparing one company with another, assuming that they are in the same industry. So if a company had net earnings of £1,000 with 200 shares issued, it would have an EPS of 5. By looking at the EPS over several years you can look for a growth pattern and compare it with the market and industry.<br>
<br>
Don't get this confused with Dividend Yield...<br>
<br>
<h3>Electronic Communication Network – ECN</h3>

An electronic system that attempts to eliminate third parties orders entered by an exchange market maker or an over-the-counter-OTC market maker, and permits such orders to be executed either in whole or in part.<br>
An ECN connects major brokerages and individual traders so that they can trade directly between themselves without having to go through a middleman.<br>
Examples of these include: Brokertec, CBOT, MTS, HDAT, Tradeweb, WebET, EUREX<br>
<br>
<h3>Emerging Markets</h3>

Markets within countries that are starting to participate globally by implementing reform programs and undergoing economic improvement. The countries that have emerging markets together represent a large percentage of the world's population while accounting for a relatively small percentage of the world's economic production. Emerging markets tend to be more volatile than established markets due to uncertainty within the political scene and domestic currency.<br>
<br>
<h3>Exchange Traded Funds (ETFs)</h3>

<a href='http://www.youtube.com/watch?v=FM6KnD75QrE'>What is an exchange traded fund? - MoneyWeek Investment Tutorials</a>

An ETF is a security, usually a share, which tracks an index, basket of stocks or a commodity (e.g. gold). This gives you direct exposure to the underlying in an easily traded and efficient way.<br>
<br>
An exchange-traded fund (ETF) is an equity-based product combining the characteristics of an individual share with those of a collective fund. Like unit or investment trusts, ETFs track a group of shares or an asset (eg gold), giving diversified exposure to a market or sector.<br>
<br>
Unlike with most funds, the shares held are defined in advance so that when you buy, you know what will be in the fund. This makes ETFs similar to standard index funds, but they're more flexible: they can be traded through a stockbroker at any time in lots of any size. And costs are low: management fees tend to be around 0.5%, as opposed to the 1% charged by most trackers. ETFs can also be held in an Individual Savings Account or a Self Invested Personal Pension.<br>
<br>
<a href='http://etfdb.com/2009/guide-to-index-weightings/'>http://etfdb.com/2009/guide-to-index-weightings/</a>

<h3>Financial Services Authority (FSA)</h3>

Government controlled regulatory body for the Financial Services Industry in the UK.<br>
<br>
<h3>Front Office</h3>

The sales and trading personnel in a financial services company. Front office workers produce the revenue.<br>
<br>
<h3>Hedge Fund</h3>

<a href='http://www.youtube.com/watch?v=2VxCq7aqCxg'>What is a hedge fund? - MoneyWeek Investment Tutorials</a>

An aggressively managed fund portfolio taking positions in both safe and speculative opportunities. Most hedge funds are limited to a maximum of 100 investors. And for the most part, hedge funds (unlike regular mutual funds) are unregulated because it is assumed that the people investing in them are very sophisticated and wealthy investors. Hedging is actually the practice of attempting to reduce risk, and the main goal of a hedge fund is to get a maximum rate of return, using strategies involving options, short selling, and leverage.<br>
<br>
<h3>Index</h3>

<a href='http://www.youtube.com/watch?v=4451A-I9fc4'>Dow Jones, FTSE 100 - What is an index? - MoneyWeek Investment Tutorials</a>

A statistical measure of change in an economy or a securities market. In the case of financial markets, an index is essentially an imaginary portfolio of securities representing a particular market or a portion of it. Each index has it's own calculation methodology and is usually expressed in terms of a change from a base value.<br>
The Financial Times 100 or FTSE is one of the world's best-known indices, and is the most commonly used benchmark for the stock market.  This is an index of blue chip stocks on the London Stock Exchange.<br>
<br>
Other links:<br>
<ul><li><a href='http://news.bbc.co.uk/1/hi/programmes/working_lunch/4652010.stm'>BBC Working Lunch - How the FTSE is calculated</a></li></ul>

<h3>Investment Bank</h3>

<a href='http://www.youtube.com/watch?v=xlYDonZLoHg'>What do investment banks actually do? - MoneyWeek Investment Tutorials</a>

A financial intermediary that performs a variety of services. This includes underwriting, acting as an intermediary between an issuer of securities and the investing public, facilitating mergers and other corporate reorganizations, and also acting as a broker for institutional clients. The role of the investment bank begins with pre-underwriting counselling and continues after the distribution of securities in the form of advice.<br>
<br>
Investment banks also perform a wide range of other functions which help to facilitate the services they offer clients or generate profit:<br>
<br>
<ul><li>Proprietary (Prop) Trading - using their own funds to gamble on the same products they sell to investors in order to make money.<br>
</li><li>Market Making - creating a market for a security by continually offering a bid-offer spread, usually in equity markets, banks commit to trade in certain securities so investors can always trade in that security. This sounds similar to prop but it's not really, market making is regulated and highly competitive so often only modest gains are possible for banks.<br>
</li><li>Mergers & Acquisitions - M&A is very a valuable business for banks, they are paid to give advice and services to companies looking to merge or acquire another firm. Banks might help their client decide how to finance the deal, when it should take place, what the regulatory and legal requirements of such a deal are, completing due diligence and publicising the deal etc.<br>
</li><li>Corporate Events or New Issues - i.e. raising funds for whatever the company wants to achieve and how they want to go about it e.g. by issuing shares (equity) or bonds (debt). New Issues might include IPOs. Banks use their extensive clients lists to try and suss out the market so they often try to sounds out their clients to assess appetite. We also go back to the underwriting activities of the bank because the bank might take a massive fee to guarantee everything gets sold.<br>
</li><li>Structured Products - custom products designed by banks for their clients and sold to investors. For more information see <a href='http://www.youtube.com/watch?v=Umx5ShOz2oU'>here</a>.</li></ul>

<b>Beware:</b> Terminology is an interesting beast, often different banks call the same activity different things and it can get quite confusing, this is one of the biggest battles when it comes to the industry.<br>
<br>
<h3>IPO (Floating)</h3>

Selling Stock - IPO is an acronym for Initial Public Offering. This is the first sale of stock by a company to the public. A company can raise money by issuing either debt (bonds) or equity. If the company has never issued equity to the public, it's known as an IPO.<br>
Companies fall into two broad categories: private and public.<br>
A privately held company has fewer shareholders and its owners don't have to disclose much information about the company. Anybody can go out and incorporate a company: just put in some money, tender the correct legal documents, and follow the reporting rules of your jurisdiction. Most small businesses are privately held. But large companies can be private too. Companies such as IKEA, Domino's Pizza, and Hallmark Cards are all privately held.  As long as there is market demand, a public company can always issue more stock. Thus, mergers and acquisitions are easier to do because stock can be issued as part of the deal. In the past, only private companies with strong fundamentals could qualify for an IPO and it wasn't easy to get listed.<br>
<br>
The Internet boom changed all this. Firms no longer needed strong financials and a solid history to go public. Instead, IPO’s were done by smaller start-ups seeking to expand their business. There's nothing wrong with wanting to expand, but most of these firms had never made a profit and didn't plan on being profitable any time soon. The IPO then becomes the end of the road rather than the beginning.<br>
<br>
<h3>LIBOR</h3>

This is the rate of interest at which banks borrow funds, in marketable size, from other banks in the London interbank market. In other words, LIBOR is the international rate that banks borrow from other banks at. This is the most widely used benchmark or reference rate for short-term interest rates.<br>
<br>
<h3>Market Makers</h3>

<a href='http://www.youtube.com/watch?v=QqK6H1JPjv0'>What are 'market makers'? - MoneyWeek Investment Tutorials</a>

Market makers provide continuous bid and offer prices within a prescribed percentage spread for shares in which they are designated to make a market. There can be anywhere from 4 to 40 (or more) market makers for a particular stock depending on the average daily volume. The market makers play an important role in the secondary market as catalysts, particularly for enhancing stock liquidity and thus generally for promoting long-term growth the market. They facilitate trading for clients and other brokerage firms.<br>
<br>
<h3>Middle Office</h3>

The group in a financial services company that draws on the resources of both the front and the back offices to manage the risk exposures. The technology infrastructure and Compliance can also be considered in the middle office.<br>
<br>
<h3>Online Trading</h3>

Trading via the Internet. The use of online trading increased dramatically in the mid to late 1990's with the advent of high-speed computers and Internet connections. Stocks, bonds, options, futures, and currencies can all be traded online.<br>
<br>
<h3>Over-The-Counter</h3>

A security, which is not traded on an exchange, usually due to an inability to meet listing requirements. For such securities, brokers/dealers negotiate directly with one another over computer networks and by phone. The NASDAQ is considered to be an OTC market, with the tier 1 being represented by companies such as Microsoft, Dell, and Intel. Also referred to as unlisted.<br>
<br>
<h3>P/E Ratio</h3>

The price/earnings ratio is a quick way to establish a firm's relative value. You get it by either dividing a firm's market capitalisation by its profits after tax, or by dividing the price of one share by the firm's earnings per share. The p/e tells you how many years it will take the firm to make profits equivalent to its market cap: if the p/e is ten, assuming profits stay the same, it will take ten years. A high p/e, or 'multiple', suggests a firm that is growing or is expected to grow fast. A high-growth firm with a low p/e could be considered cheap, and a low-growth firm with a high p/e could be considered expensive. The p/e is the main measure analysts use to determine a company's position relative to the rest of the market.<br>
<br>
Money Week have two videos on P/E Ratios, one <a href='http://www.youtube.com/watch?v=zAtMjVUnXkM'>explaining what they are</a> and a second <a href='http://www.youtube.com/watch?v=jF8uOzgbLEs'>explaining their beartraps to avoid</a>. An alternative explanation can also be found <a href='http://stocks.about.com/od/evaluatingstocks/a/pe.htm'>here</a>.<br>
<br>
<h3>Portfolio Trading</h3>

The trading of a group of different investments. This is the art and science of making decisions about: investment mix and policy, matching investments to objectives, asset allocation for individuals and institutions, and balancing risk vs. performance.<br>
Portfolio management is all about strengths, weaknesses, opportunities, and threats in the choice of debt vs. equity, domestic vs. international, growth vs. safety, and numerous other trade-offs encountered in the attempt to maximize return at a given appetite for risk.<br>
<br>
<h3>Primary Market</h3>

The market in which investors have the first opportunity to buy a newly issued security. After the first purchases, subsequent trading is said to occur in the secondary market.<br>
<br>
<h3>Prime Brokerage</h3>

A special group of services that many brokerages give to special clients. The services provided under prime brokering are securities lending, leveraged trade executions, and cash management, among other things. Prime brokerage services are provided by most of the large brokers, such as Goldman Sachs, Paine Webber, and Morgan Stanley Dean Witter.<br>
Hedge funds were what started the prime brokerage option. Hedge funds place large trades and need special attention from brokerages.<br>
<br>
<h3>Quantitative Analysis</h3>

A security analysis that uses financial information derived from company annual reports and income statements to evaluate an investment decision. Some examples are financial ratios, the cost of capital, asset valuation, and sales and earnings trends.<br>
<br>
<h3>Quantitative Easing (QE)</h3>

Extracted from: <a href='http://www.moneyweek.com/investment-advice/glossary'>MoneyWeek Glossary</a> and <a href='http://www.youtube.com/watch?v=NiD9FSp_tMw'>What is quantitative easing all about? - MoneyWeek Investment Tutorials</a>

Quantitative easing (QE) involves electronically expanding a central bank's balance sheet. So under its Asset Purchase Facility the Bank of England will buy directly from commercial banks "high-quality assets, broadly comparable to investment grade". These will range from fixed-income sovereign debt and corporate bonds to 'asset-backed' securities built on property loans. On the flipside, the Bank will also sell fewer of its own IOUs – gilts – to institutions such as pension funds. The two measures combined should release extra liquidity into the economy.<br>
<br>
'Monetarist' theory says that both economic activity and price levels are set by the amount of money available multiplied by the speed at which it moves around ('the velocity of money'). So, by physically upping the cash that commercial lenders have at their disposal – by buying assets from them that other banks may be reluctant to accept as security for loans – the idea is that quantitative easing will directly boost interbank lending and raise the flow of credit through the economy as banks regain the confidence to make other loans to, say, small businesses and homeowners.<br>
<br>
<h3>Riskless Principal</h3>

Riskless principal is defined by the Financial Industry Regulatory Authority (FINRA) as a trade in which a member who has received a customer order immediately executes an identical order in the marketplace, while taking on the role of principal, in order to fill the customer order. More <a href='http://www.investopedia.com/terms/r/risklessprincipal.asp'>here</a>.<br>
<br>
<h3>Sarbanes Oxley for Dummies</h3>

<ul><li><a href='http://www.dummies.com/how-to/content/sarbanesoxley-for-dummies-cheat-sheet.html'>Sarbanes Oxley for Dummies</a></li></ul>

<h3>Secondary Market</h3>

A market in which an investor purchases an asset from another investor, rather than an issuing corporation. A good example is the London Stock Exchange. All stock exchanges are part of the secondary market, as investors buy securities from other investors instead of an issuing company.<br>
<br>
<h3>Securities Lending or Stock Loan Business</h3>

When a brokerage lends securities owned by its clients to short sellers. This allows brokers to create additional revenue (commissions) on the short sale transaction.<br>
<br>
<h3>Security</h3>

An instrument representing ownership (stocks), a debt agreement (bonds), or the rights to ownership (derivatives). Examples of a security include a note, stock, preferred share, bond, debenture, option, future, swap, right, warrant, or virtually any other financial asset.<br>
<br>
<h3>Short Selling</h3>

Selling short is the opposite of going long. That is, short sellers make money if the stock goes down in price. Usually in order to sell a security short the investor must borrow that security before hand, which of course costs money, if you don't borrow it first you are said to be a naked short seller.<br>
<br>
<h3>Straight Through Processing (STP)</h3>

An IT initiative by many financial companies to streamline transactions by maintaining contact throughout processing.  STP allows companies to move from Today + 3 working days to same day settlement by removing redundant operations and utilising electronic transfers.<br>
<br>
<h3>Time Value of Money</h3>

<a href='http://www.youtube.com/watch?v=h3nlIOhxBF4'>What is the 'time value of money'? - MoneyWeek Investment Tutorials</a>

The Time Value of Money is absolutely crucial for anything that you're valuing, whether that's a bond or a share or whatever.<br>
<br>
<h3>Volatility</h3>

1. In general, volatility is a statistical measure of the tendency of a market or security to rise or fall sharply within a short period of time. Volatility is typically calculated by using variance or annualised standard deviation of the price or return.<br>
<br>
2. Volatility is a variable that appears in option pricing formulas. In the option pricing formula, it denotes the extent to which the return of the underlying asset will fluctuate between now and the expiration of the option.<br>
<br>
A highly volatile market means that prices have huge swings in very short-periods of time.<br>
<br>
<hr />

<h2>Equities</h2>

<h3>Equity</h3>

1. A term describing stock, or any security, representing an ownership interest.<br>
<br>
2. On the balance sheet, equity refers to the value of the funds contributed by the owners (the stockholders) plus the retained earnings (or losses).<br>
<br>
3. In the context of margin trading, equity is the value of securities minus what has been borrowed from the brokerage.<br>
<br>
Equity is a term whose meaning depends very much on the context. In general, you can think of equity as ownership. For example, stocks or shares are equity because they represent ownership of a company, whereas bonds are classified as debt because they represent an obligation to pay and not ownership of assets.<br>
<br>
On the balance sheet, equity is also referred to as shareholder's equity.<br>
<br>
The Definition of a Stock<br>
Plain and simple, stock is a share in the ownership of a company. Stock represents a claim on the company's assets and earnings. As you acquire more stock, your ownership stake in the company becomes greater. Whether you say shares, equity, or stock, it all means the same thing.<br>
<br>
<h3>Debt vs. Equity</h3>

Why does a company issue stocks/shares? Why would the founders share the profits with thousands of people when they could keep profits to themselves? The reason is that at some point every company needs to raise money. To do this, companies can either borrow it from somebody or raise it by selling part of the company, which is known as issuing stock. A company can borrow by taking a loan from a bank or by issuing bonds. Both methods fit under the umbrella of "debt financing." On the other hand, issuing stock is called "equity financing." Issuing stock is advantageous for the company because it does not require the company to pay back the money or make interest payments along the way. All that the shareholders get in return for their money is the hope that the shares will some day be worth more. The first sale of a stock, which is issued by the private company itself, is called the initial public offering (IPO).<br>
<br>
It is important to understand the distinction between a company financing through debt and financing through equity. When you buy a debt investment such as a bond, you are guaranteed the return of your money (the principal) along with promised interest payments. This isn't the case with an equity investment. By becoming an owner, you assume the risk of the company not being successful.<br>
<br>
<h3>Market Data Levels</h3>

<a href='http://en.wikipedia.org/wiki/Financial_quote'>http://www.londonstockexchange.com/prices-and-markets/stocks/tools-and-services/level2/level2guide.pdf LSE Level 2 Guide</a>

The current BID price of a security is what people are willing to pay to buy the instrument; whereas the current ASK price is what people are willing the sell it for.<br>
<br>
<h4>Level 1</h4>

Level 1 Market Data displays the basics market data of an instrument, e.g. the current best bid & ask (and perhaps their sizes), market volume, high, mid, low prices, VWAP, yesterday's closing price and other detailed information.<br>
<br>
<h4>Level 2</h4>

Level 2 Market Data on the other hand shows market depth, starting with the current best bid and ask prices and list the rest of the order book in either direction along with the current volume at each price, commonly you'll also get a cumulative quantity of all the shares at or below/above that price.<br>
<br>
<hr />

<h2>Fixed Income</h2>

<h3>Fixed-Income Security</h3>

An investment that provides a return in the form of fixed periodic payments and eventual return of principle at maturity.<br>
Generally, these types of assets offer a lower return on investment because they guarantee income.<br>
<br>
<h3>Bonds</h3>

<a href='http://www.youtube.com/watch?v=AqTjNU7mQZQ'>The basics of bonds - MoneyWeek Investment Tutorials</a>
<a href='http://www.youtube.com/watch?v=xVcDCsHF_HY'>Bonds basics part two - MoneyWeek Investment Tutorials</a>
<a href='http://www.youtube.com/watch?v=3b69Ax5m7qg'>What is a yield curve? - MoneyWeek Investment Tutorials</a>
<a href='http://www.youtube.com/watch?v=eErg78D6GoA'>Do we need rating agencies? - MoneyWeek Investment Tutorials</a>
<a href='http://www.xtrakter.com/education.aspx'>Bond FAQ from xtrakter</a>

Bonds are debt investments, with which the investor loans money to an entity (company or government) that borrows the funds for a defined period of time at a specified interest rate.<br>
The indebted entity issues investors a certificate, or bond, that states the interest rate (coupon rate) that will be paid and when the loaned funds are to be returned (maturity date). Interest on bonds is usually paid every six months (semi-annually). The main types of bonds are:<br>
<ul><li>The corporate bond - a security issued by a company but no ownership is implied, in the case of a company going bust bond holders are paid back first making them less risky than say an equity stake<br>
</li><li>The municipal bond - a security issued by or on behalf of a local authority<br>
</li><li>The Treasury bond - A government bond issued by the US Treasury<br>
</li><li><a href='http://www.investopedia.com/terms/t/treasurynote.asp'>The Treasury note</a> - An interest-bearing security issued by the US Treasury for use as currency (i.e. very liquid), usually with original maturity of two to ten years.<br>
</li><li><a href='http://www.investopedia.com/terms/t/treasurybill.asp'>The Treasury bill</a> - A short-dated government security, yielding no interest but issued at a discount on its redemption price (i.e. below par).<br>
</li><li>The zero-coupon bonds - a bond that is issued at a deep discount from its value at maturity and pays no interest during the life of the bond, you need to consider the time value of money when calculating your rate of return on this type of bond.<br>
</li><li>Floating Rate Notes (FRNs) - a bond with a variable coupon payment. These bonds link the coupon payments to a measure of current market rates. For example, the coupon rate might be linked to the T-bill rate plus 2.5% thus ensuring that the bond's coupon rate always follows the current market interest rate.</li></ul>

The higher rate of return the bond offers, the more risky the investment. There have been instances of companies failing to pay back the bond (default), so, to entice investors, most corporate bonds will offer a higher return than a government bond. It is important for investors to research a bond just as they would a stock or mutual fund. The bond rating will help in deciphering the default risk.<br>
<br>
The first thing that comes to most people's minds when they think of investing is the stock market. After all, stocks are exciting. The swings in the market are scrutinized in the newspapers and even covered by local evening newscasts. Stories of investors gaining great wealth in the stock market are common.<br>
<br>
Bonds, on the other hand, don't have the same sex appeal. The lingo seems arcane and confusing to the average person. Plus, bonds are much more boring, especially during raging bull markets (favourable stock market conditions), when they seem to offer an insignificant return compared to stocks.<br>
<br>
However, all it takes is a bear market (the opposite of a Bull) to remind investors of the virtues of a bond's safety and stability. In fact, it makes sense for many investors to have at least part of their portfolio invested in bonds.<br>
<br>
<h3>Convertible Bonds</h3>

A bond that can be converted into a predetermined amount of the company's equity at certain times during its life. Convertibles are sometimes called CVs. Convertible bonds tend to offer a lower rate of return in exchange for the option to trade the bond into stock.<br>
<br>
<h3>Gilt-Edged Bond</h3>

A bond that is issued by a blue chip (FT-SE 100 listed) company. These bonds are considered to be high grade, with little risk of interest payment interruption or default. This is the closest you can get to a government issue without actually buying one.<br>
<br>
<h3>Government Bond</h3>

A government debt obligation (local or national) backed by the credit and taxing power of a country with very little risk of default. This includes short-term treasury bonds, medium-term treasury notes, and long-term treasury bonds.<br>
<br>
<h3>Junk Bond</h3>

A bond rated usually BB or lower because of its high default risk. Also known as a high-yield bond. Usually purchased for speculative purposes. Junk bonds typically offer interest rates three to four percentage points higher than safer government issues.<br>
<br>
<h3>Bond Prices & Yields</h3>

Also see <a href='http://en.wikipedia.org/wiki/Bond_valuation'>Bond Valuation</a>.<br>
<br>
Bonds are traded just like any other asset in the open market, based on supply and demand. We price a bond using the yield-to-maturity (the annual rate of return on a financial asset that is held until maturity, essentially capturing two key factors in bonds, the interest rate and the time to redemption) of bonds with a similar risk profile to the bond to be priced and it's coupon. Therefore if a bond pays a coupon less than the current yield to maturity then the bonds market value will be less than it's face value.<br>
<br>
Using the formula below we can work out the price of a five year 4% (annual) coupon bond with a face value of $100, given that the yield to maturity for a similar bond is 8%.<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202012-12-01%20at%2014.18.41.png' />

<b>NOTE</b> You can calculate this very easily using Excel's PV function.<br>
<br>
What about the same bond paying 4% semi-annually? Well, if the bond makes semi-annual coupon payments, the annual yield is converted to a semi-annual yield simply by dividing it by two, as such the formula is:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/semi_formula.gif' />

Resulting in:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/semi_calc.gif' />

<b>NOTE</b> For FRNs we make a slight adjustment to the formula to account for the variation in rates:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/frn_calc.gif' />

We ascertain that bond prices and yields are inversely proportional:<br>
<br>
<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202012-12-01%20at%2013.44.31.png' />

<h4>Clean & Dirty Prices</h4>

A <b>clean price</b> is the price we would calculate by discounting the coupon payments and redemption value at the appropriate rate for the bond. The price that is <i>actually</i> paid, however, is the <b>dirty price</b>, which is equal to the clean price plus accrued interest (i.e. what the holder of the bond should have earned in interest from the last coupon payment to the date of the sale).<br>
<br>
<h4>Type of Yield</h4>

<h5>General Definition</h5>
The yield is the rate of interest implied by the future payment structure of the bond and the current price.<br>
<br>
<h5>Yield to Maturity (YTM)</h5>

<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202012-12-01%20at%2016.29.48.png' />

<h5>Current Yield (Flat Yield/Interest Yield)</h5>

<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202012-12-01%20at%2016.30.00.png' />

<h5>Holding Period Yield</h5>

<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202012-12-01%20at%2016.30.15.png' />

<h5>Forward Yield</h5>

<img src='http://thesandbox.googlecode.com/files/Screen%20shot%202012-12-01%20at%2016.30.30.png' />

<h4>Interest Rate Risk</h4>

We have seen that bonds suffer from interest rate risk. Bond investors gain when interest rates decrease because the prices of their bonds increase, but they lose when the opposite occurs.<br>
<br>
Bonds have several defining characteristics:<br>
<br>
<ul><li>maturity<br>
</li><li>coupon<br>
</li><li>yield price</li></ul>

Bonds with different characteristics are affected differently by changes in market interest rates. One very important characteristic is the maturity of the bond, which defines the sensitivity of the bond to interest rate risk. When market interest rates increase, longer-term bonds suffer a greater decrease in price than shorter-term bonds. However, when interest rates decrease, longer-term bonds increase in price more than shorter-term bonds.<br>
<br>
When it comes to pricing a particular bond a lot has to do with the issuer, what are the chances that the issuer will default? This is where the ratings agencies (Standard & Poors, Moodys & Fitch) come in and assess their credit worthiness.<br>
<br>
<hr />

<h2>FX / Money Markets</h2>

The money market is a subsection of the fixed income market. We generally think of the term "fixed income" as synonymous with bonds. In reality, a bond is just one type of fixed income security. The difference between the money market and the bond market is that the money market specializes in very short-term debt securities (debt that matures in less than one year). Money market investments are also called cash investments because of their short maturities.<br>
Money market securities are essentially IOUs issued by governments, financial institutions, and large corporations. These instruments are very liquid and considered extraordinarily safe. Because they are extremely conservative, money market securities offer significantly lower return than most other securities.<br>
<br>
Whenever a bear market comes along, investors realise that the stock market is a risky place for their savings. To get higher returns, you have to take on a higher level of risk. For many investors, a volatile market is too much to stomach - an alternative is the money market.<br>
<br>
The money market is better known as a place for large institutions and government to manage their short-term cash needs. However, individual investors have access to the market through a variety of different securities. We will learn about money market instruments in this tutorial.<br>
<br>
<h3>Forex Market</h3>

The common term for the foreign exchange market. Brokerage firms and banks are connected over an electronic network that allows them to convert the currencies of most countries. The forex market trades trillions of dollars each and every day<br>
Did you know that the foreign exchange market (also known as FX or Forex) is the largest market in the world? In fact over $1 trillion is traded in the currency markets on a daily basis.<br>
<br>
<h3>Exchange Rate</h3>

An exchange rate is the rate at which one currency can be exchanged for another. In other words, it is the value of another country's currency compared to that of your own. If you are travelling to another country, you need to "buy" the local currency. Just like the price of any asset, the exchange rate is the price at which you can buy that currency. If you are travelling to the US, for example, and the exchange rate for GBP1 is US$1.60, this means that for every pound, you can buy one dollar and sixty cents. Theoretically, identical assets should sell at the same price in different countries, because the exchange rate must maintain the inherent value of one currency against the other.<br>
<br>
<h3>Fixed and Floating Exchange Rates</h3>

<h4>Fixed</h4>
There are two ways the price of a currency can be determined against another. A fixed, or pegged, rate is a rate the government (central bank) sets and maintains the official exchange rate. A set price will be determined against a major world currency (usually the U.S. dollar, but also other major currencies such as sterling, the euro, the yen, or a basket of currencies). In order to maintain the local exchange rate, the central bank buys and sells its own currency on the foreign exchange market in return for the currency to which it is pegged.<br>
<br>
<h4>Floating</h4>
Unlike the fixed rate, a floating exchange rate is determined by the private market through supply and demand. A floating rate is often termed "self-correcting," as any differences in supply and demand will automatically be corrected in the market. Take a look at this simplified model: if demand for a currency is low, its value will decrease, thus making imported goods more expensive and thus stimulating demand for local goods and services. This in turn will generate more jobs, and hence an auto-correction would occur in the market. A floating exchange rate is constantly changing.<br>
<br>
<h3>Repos</h3>

<a href='http://www.youtube.com/watch?v=sbPByb-NXdQ'>What is a repo? - MoneyWeek investment tutorials</a>

A form of short term borrowing for dealers in government securities. The dealer sells the government securities to investors, usually on an overnight basis, and buys them back the following day (plus a little bit of interest, the repo-rate). In addition to the repo-rate the lender of the cash might also impose a haircut, which is a way of offsetting the risk of the securities changing hand decreasing in value, this haircut is one reason why government securities are often chosen because the risk is low(er) and therefore the haircut in reduced as such.<br>
<br>
For the party selling the security (and agreeing to repurchase it in the future) it is a Repo; for the party on the other end of the transaction (buying the security and agreeing to sell in the future) it is a reverse repurchase agreement. It works in both ways because the buying party may need hold the securities (collateral) for a short period of time for fulfil other obligations e.g. short-selling.<br>
<br>
Repos are classified as a money-market instrument. They are usually used to raise short-term capital.<br>
<br>
<a href='https://twitter.com/TimMoneyweek'>Tim Bennett</a> calls this <b>organised pawn-broking</b>, he also says that it was a contributing factor to the downfall of Lehman Brothers, Bear Stearns & MF Global.<br>
<br>
<hr />

<h2>Commodities</h2>

Any bulk good traded on an exchange or in the cash market. Some examples include grain, oats, gold, oil, beef, silver, and natural gas, electricity, FCOJ (Frozen Concentrated Orange Juice), Pork Bellies, Coffee and Tea.<br>
<br>
<hr />

<h2>Derivatives</h2>

<a href='http://www.youtube.com/watch?v=Wjlw7ZpZVK4'>What are derivatives? - MoneyWeek Investment Tutorials</a>

<h3>Derivatives</h3>

A security, such as an option or futures contract, whose value depends on the performance of an underlying security.<br>
<br>
A derivative is a financial instrument that does not constitute ownership, but a promise to convey ownership. Examples are options and futures. Before discussing derivatives, it's important to describe their basis. All derivatives are based on some underlying cash product. These "cash" products are:<br>
<br>
<ul><li><b>Spot Foreign Exchange.</b> This is the buying and selling of foreign currency at the exchange rates that you see quoted on the news. As these rates change relative to your "home currency" (sterling if you are in the UK), so you make or lose money.</li></ul>

<ul><li><b>Commodities.</b> These include grain, pork bellies, coffee beans, orange juice, etc.</li></ul>

<ul><li><b>Equities</b> (termed as stocks or shares)</li></ul>

<ul><li><b>Bonds</b> of various different varieties (e.g., they may be Eurobonds, domestic bonds, fixed interest / floating rate notes, etc.). Bonds are medium to long-term negotiable debt securities issued by governments, government agencies, supra-national organisations such as the World Bank and companies. Negotiable means that they may be freely traded without reference to the issuer of the security. That they are debt securities means that in the event that the company goes bankrupt. Bondholders will be repaid their debt in full before the holders of unsecuritised debt get any of their principal back.</li></ul>

<ul><li><b>Short term ("money market") negotiable debt securities</b> such as Treasury Bonds (issued by governments), Commercial Paper (issued by companies) or Bankers Acceptances. These are much like bonds, differing mainly in their maturity. "Short" term is usually defined as being up to 1 year in maturity, "medium-term" is commonly taken to mean from 1 to 5 years in maturity, and "long term" anything above that.</li></ul>

<ul><li><b>Over the Counter ("OTC") money market products</b> such as loans / deposits. These products are based upon borrowing or lending. They are known as "over the counter" because each trade is an individual contract between the 2 counterparties making the trade. They are neither negotiable nor securitised. Hence if I lend your company money, I cannot trade that loan contract to someone else without your prior consent. Additionally if you default, I will not get paid until holders of your company's debt securities are repaid in full. I will however, be paid in full before the equity holders see a penny.</li></ul>

Derivative products are contracts which have been constructed based on one of the "cash" products described above. Examples of these products include options and futures.<br>
<br>
<h3>Futures</h3>

<a href='http://www.youtube.com/watch?v=nwR5b6E0Xo4'>What are Futures - MoneyWeek Investment Tutorials</a>

Futures are commonly available in the following flavours (defined by the underlying "cash" product):<br>
<br>
Commodity futures<br>
Equity index futures<br>
Interest rate futures (including deposit futures, bond futures and government bond futures)<br>
<br>
Futures contracts, forward contracts, and options are the most common types of derivatives. Derivatives are generally used by institutional investors to increase overall portfolio return or to hedge portfolio risk.<br>
<br>
<h3>Options</h3>

<a href='http://www.youtube.com/watch?v=3196NpHDyec'>What are Options &amp; Covered Warrants - MoneyWeek Investment Tutorials</a>

An option is a contract giving the buyer the right, but not the obligation, to buy or sell an underlying asset at a specific price on or before a certain date. An option, just like a stock or bond, is a security. It is also a binding contract with strictly defined terms and properties.<br>
<br>
The idea behind an option is present in several everyday situations. Say for example you discover a house that you'd love to purchase. Unfortunately, you won't have the cash to buy it for another three months. You talk to the owner and negotiate a deal that gives you an option to buy the house in three months for a price of £200,000. The owner agrees, but for this option, you pay a price of £3,000.<br>
Now, consider two theoretical situations that might arise:<br>
1. It's discovered that the house is actually the true birthplace of John Lennon! As a result, the market value of the house rockets to £1,000,000. Because the owner sold you the option, he is obligated to sell you the house for £200,000. In the end, your profit is £797,000 (£1,000,000 - £200,000 - £3,000).<br>
2. While touring the house, you discover not only that the walls are chock-full of asbestos, but also that the ghost of Henry VII haunts the master bedroom; furthermore, a family of super-intelligent rats have built a fortress in the basement. Though you originally thought you had found the house of your dreams, you now consider it worthless. On the upside, because you bought an option, you are under no obligation to go through with the sale. Of course, you still lose the £3,000 price of the option.<br>
<br>
This example demonstrates two very important points. First, when you buy an option, you have a right but not the obligation to do something. You can always let the expiration date go by, at which point the option is worthless. If this happens, you lose 100% of your investment, which is the money you used to pay for the option. Second, an option is merely a contract that deals with an underlying asset. For this reason, options are called derivatives, which means an option derives its value from something else. In our example, the house is the underlying asset. Most of the time, the underlying asset is a stock or an index.<br>
<br>
<h3>Futures/Options/Derivatives</h3>

What we know as the futures market of today came from some humble beginnings. Trading in futures originated in Japan during the 18th century and was primarily used for the trading of rice and silk. It wasn't until the 1850s that the U.S. started using futures markets to buy and sell commodities such as cotton, corn and wheat.<br>
<br>
A futures contract is a type of derivative instrument, or financial contract, in which two parties agree to transact a set of financial instruments or physical commodities for future delivery at a particular price. If you buy a futures contract, you are basically agreeing to buy something, for a set price, that a seller has not yet produced. But participating in the futures market does not necessarily mean that you will be responsible for receiving or delivering large inventories of physical commodities—remember, buyers and sellers in the futures market primarily enter into futures contracts to hedge risk or speculate rather than exchange physical goods (which is the primary activity of the cash/spot market). That is why futures are used as financial instruments by not only producers and consumers but also speculators.<br>
<br>
The consensus in the investment world is that the futures market is a major financial hub, providing an outlet for intense competition among buyers and sellers and, more importantly, providing a centre to manage price risks. The futures market is extremely liquid (fluid), risky, and complex by nature, but it can be understood if we break down how it functions.<br>
<br>
While futures are not for the risk-averse, they are useful for a wide range of people. In this tutorial, you'll learn how the futures market works, who uses futures and why, and which strategies will make you a successful trader on the futures market.<br>
<br>
<h3>The Black and Scholes Option Pricing Model</h3>

The Black and Scholes Option Pricing Model is an approach for calculating the value of a stock option. This article presents some detail about the pricing model.<br>
The Black and Scholes Option Pricing Model didn't appear overnight, in fact, Fisher Black started out working to create a valuation model for stock warrants. This work involved calculating a derivative to measure how the discount rate of a warrant varies with time and stock price. The result of this calculation held a striking resemblance to a well-known heat transfer equation. Soon after this discovery, Myron Scholes joined Black and the result of their work is a startlingly accurate option pricing model. Black and Scholes can't take all credit for their work; in fact their model is actually an improved version of a previous model developed by A. James Boness in his Ph.D. dissertation at the University of Chicago. Black and Scholes' improvements on the Boness model come in the form of a proof that the risk-free interest rate is the correct discount factor, and with the absence of assumptions regarding investor's risk preferences.<br>
<br>
<h3>Common types of Derivative</h3>

<h4>Asset Swap</h4>

Similar in structure to a plain vanilla swap, the key difference is the underlying of the swap contract. Rather than regular fixed and floating loan interest rates being swapped, fixed and floating investments are being exchanged. In a plain vanilla swap, a fixed libor is swapped for a floating libor. In an asset swap, a fixed investment such as a bond with guaranteed coupon payments is being swapped for a floating investment such as an index.<br>
<br>
<h4>Bond Option</h4>

A derivative investment in which the underlying asset is a bond. Similar to a stock option, except the underlying asset is a corporate or government bond.<br>
<br>
<h4>Credit Derivatives</h4>

Privately held negotiable bilateral contracts that allow users to manage their exposure to credit risk. Credit Derivatives are financial assets like forward contracts, swaps, and options for which the price is driven by the credit risk of economic agents (private investors or governments). For example, a bank concerned that one of its customers may not be able to repay a loan can protect itself against loss by transferring the credit risk to another party while keeping the loan on its books.<br>
<br>
<h4>Credit Default Swaps (CDS’)</h4>

A swap designed to transfer the credit exposure of fixed income products between parties. For example, the buyer of a credit swap will be entitled to the par value of the bond by the seller of the swap, should the bond default in its coupon payments.<br>
<br>
<h4>Collateralized Debt Obligation (CDO’s)</h4>

An investment-grade security backed by a pool of bonds, loans, and other assets. CDOs do not specialize in one type of debt. Similar in structure to a Collateralized mortgage obligation (CMO) or Collateralized bond obligation (CBO), but unique in that CDOs represent different types of debt and credit risk<br>
<br>
<h4>Currency/FX Option</h4>

A contract that grants the holder the right, but not the obligation, to buy or sell currency at a specified price during a specified period of time.<br>
Investors can hedge against foreign currency risk by purchasing a currency.<br>
<br>
<h4>Currency/FX Forward</h4>

A forward contract that locks-in the price an entity can buy or sell currency on a future date. In currency forward contracts, the contract holders are obligated to buy or sell the currency at a specified price, at a specified quantity, and on a specified future date. These contracts cannot be transferred.<br>
Also known as "outright forward currency transaction."<br>
<br>
<h4>Currency/FX Futures</h4>

A transferable futures contract that specifies the price at which a specified currency can be bought or sold at a future date. Currency future contracts allow investors to hedge against foreign exchange risk. Since these contracts are marked-to-market daily, investors can -by closing out their position- exit from their obligation to buy or sell the currency prior to the contract's delivery date.<br>
<br>
<h4>Exotic Options</h4>

Any non-standard option. This is the opposite of a plain vanilla option.<br>
<br>
<h4>Interest Rate/Fixed Income Derivatives</h4>

These are traded on fluctuations in Interest Rates. All you need to know is that Interest Rate changes have an impact on the price / yield, and therefore this area will make money in a good or bad market.  Emerging Markets is closely linked to this as they, the traders, are gambling on fluctuations in interest rates within volatile countries such as Argentina.<br>
<br>
<h4>Interest Rate Swap (IRS)</h4>

<a href='http://www.youtube.com/watch?v=uVq384nqWqg'>What is a swap? - MoneyWeek Investment Tutorials</a>

Interest Rate Swaps are fairly simple in theory, two entities want to borrow money from a bank, on which they will both pay an interest rate. Depending on their view on the market they may wish pay a either a fixed or a variable rate of interest. When they want to pay different types a Swaps Bank can sit in between both of them and use the credit ratings of either side to the advantage of all three parties, they do this by getting them to take loans at a fixed rate if they wanted variable or vice versa, the Swaps bank then switches the fixed/variable rate and makes a profit itself.<br>
<br>
A deal between banks or companies where borrowers switch floating-rate loans for fixed rate loans in another country. These can be either the same or different currencies. The advantage to this is that one company may have access to lower fixed rates and another company may have access to lower floating rates... so they trade.<br>
<br>
<h4>Forward Rate Agreement (FRA’s)</h4>

A forward contract that determines an interest rate to be paid or received on an obligation beginning at a start date sometime in the future. Also referred to as a "Future Rate Agreement." Any gain or loss on the agreement is like a gain or loss on an option or futures contract.<br>
<br>
<h4>Mortgage Backed Securities (MBS’)</h4>

An investment instrument that represents ownership of an undivided interest in a group of mortgages. Principal and interest from the individual mortgages are used to pay principal and interest on the MBS. When you invest in a mortgage-backed security you are lending money to a homebuyer or business. MBS are a way for smaller regional banks to lend mortgages to their customers without having to worry if they have the assets to cover the loan. Instead, they act as a middleman between the homebuyer and the investment markets.<br>
<br>
<h4>OTC Options/Derivatives</h4>

Exotic options traded on the “over the counter market”.  In the over the counter exotic options market the participants can choose the characteristics of the options traded. The flexibility of these options are attractive to many.  By not having to abide by the restrictions that normal standardized exchanges place on options both hedgers and speculators can benefit.  The flexibility allows participants to more perfectly and in a less costly manner reflect their desired position.<br>
Swaption (Swap Option)<br>
<br>
The option to enter into an interest rate swap. In exchange for an option premium, the buyer gains the right, but not the obligation, to enter into a specified swap agreement with the issuer on a specified future date. The agreement will specify whether the buyer of the swaption will be a fixed-rate receiver (like a call option on a bond) or a fixed-rate payer (like a put option on a bond).<br>
<br>
<h4>Vanilla Options</h4>

A normal option with no special or unusual features. A "plain vanilla option" is a regular option, the opposite of which is an exotic option.<br>
<br>
<h4>Volatility Swap</h4>

A forward contract whose underlying is the volatility of a given product. This is a pure volatility instrument allowing investors to speculate solely upon the movement of a stock's volatility without the influence of its price. Thus, just like investors trying to speculate on the prices of stocks, by using this instrument investors are able to speculate on how volatile the stock will be.<br>
<br>
<h2>Fixed Income Deal Types</h2>

<h3>Basis Trade</h3>

A Basis trade is a transaction in which a bond and a future are the instruments, the seller of the bond would be the buyer of the future and vice versa.<br>
<br>
<h3>Cash Trade</h3>

A Cash trade simply see one bond change hands.<br>
<br>
<h3>Spread Trade</h3>

A Spread trade sees two bonds being traded, each side buys one bond and sells the other.