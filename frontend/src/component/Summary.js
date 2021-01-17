import React, { Component } from 'react';
import { Container, Table } from 'reactstrap';

class Summary extends Component {
    state = { 
        PriceChange:[],
        TempData: 0,
     }

    async componentDidMount() {
        let response = await fetch('api/dailytick/pricechange');
        let body = await response.json();
        this.setState({PriceChange: body});
        // console.log(body2);
    }

    render() { 
        const {PriceChange} = this.state;

        let rows = 
        PriceChange.map((PriceChange, index) => 
                <tr>
                    <td>{index + 1}</td>
                    {/* Name */}
                    <td style={{ fontWeight: 'bold' }}>{PriceChange[0]}</td>
                    {/* Price */}
                    <td>${PriceChange[1].toLocaleString()}</td>
                    {/* 24h */}
                    <td style={{color: PriceChange[2] >= 0 ? "green" : "red"}}>{PriceChange[2]}%</td>
                    {/* 7d */}
                    <td style={{color: PriceChange[2] >= 0 ? "green" : "red"}}>{PriceChange[3]}%</td>
                    {/* 1m */}
                    <td style={{color: PriceChange[2] >= 0 ? "green" : "red"}}>{PriceChange[4]}%</td>
                    {/* vol */}
                    <td style={{ textAlign: "right" }}>${PriceChange[5].toLocaleString()}</td>
                    {/* marketCap */}
                    <td style={{ textAlign: "right" }}>${PriceChange[6].toLocaleString()}</td>
                </tr>   
        )

        return ( 
        <div>
            <Container>
                <br/>
                <h3 style={{textAlign:"center"}}>Summary</h3>
                <Table className="mt-5">
                    <thead>
                        <th>#</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th width="10%">24H</th>
                        <th width="10%">7D</th>
                        <th width="10%">28D</th>
                        <th style = {{textAlign: "center"}}width="10%">Volume</th>
                        <th style = {{textAlign: "center"}}width="10%">Market Cap</th>
                    </thead>
                    <tbody>
                        {rows}
                    </tbody>
                </Table>
            </Container>
        </div> );
    }
}
 
export default Summary;