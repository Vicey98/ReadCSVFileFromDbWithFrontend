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
                    <td>{this.state.TempData}</td>
                    <td>{PriceChange[0]}</td>
                    <td>{PriceChange[1]}</td>
                    <td>{PriceChange[2]}</td>
                    <td>{PriceChange[3]}</td>
                    <td>{PriceChange[4]}</td>
                    <td>{PriceChange[5]}</td>
                    <td>{PriceChange[6]}</td>
                    <td>{PriceChange[7]}</td>
                </tr>   
        )

        return ( 
        <div>
            <Container>
                <br/>
                <h3 style={{textAlign:"center"}}>Summary</h3>
                <br/>
                <Table className="mt-20">
                    <thead>
                        <th>#</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th width="10%">24H</th>
                        <th width="10%">7D</th>
                        <th width="10%">28D</th>
                        <th>Volume</th>
                        <th>Market Cap</th>
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