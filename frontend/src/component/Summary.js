import React, { Component } from 'react';
import { Container, Table } from 'reactstrap';

class Summary extends Component {
    state = { 
        Data: [],
        tempData: 0,
     }

    async componentDidMount() {
        const response = await fetch('api/DailyTick/Summary');
        const body = await response.json();
        this.setState({Data: body});
        console.log(body);
    }

    render() { 

        const {Data} = this.state;

        let rows = 
            Data.map( Data =>
                <tr>
                    <td>{this.state.tempData}</td>
                    <td>{Data[0]}</td>
                    <td>{this.state.tempData}</td> 
                    {/* price */}
                    <td>{Data[1]}</td>
                    {/* 24hr */}
                    <td>{Data[2]}</td>
                    <td>{this.state.tempData}</td>
                    <td>{this.state.tempData}</td>
                    {/* volume */}
                    <td>{this.state.tempData}</td>
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