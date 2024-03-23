import { useRef, useEffect } from "react";
import { useAuth } from "../authentication/AuthContext";

export default function TableCanvas({table, size}){
    
    const {username} = useAuth();
    const canvasRef = useRef(null);

    function drawTable(canvas, ctx){

      ctx.fillStyle = '#192841';
      ctx.fillRect(0,0, canvas.width, canvas.height);
  
      ctx.beginPath();
      ctx.fillStyle = 'green';
      ctx.strokeStyle = 'brown';
      ctx.lineWidth = 2;
      ctx.ellipse(canvas.width / 2, canvas.height / 2, canvas.width / 3, canvas.height / 3, 0, 0, Math.PI * 2);
      ctx.fill();
      ctx.stroke();
    }

    function drawPlayerRectangle(canvas, ctx, player, x, y, rectangleWidth, rectangleHeight){

      ctx.fillStyle = '#f0f0f0';
      ctx.fillRect(x, y, rectangleWidth, rectangleHeight);

      //drawing player`s title
      let title;
      if(player.client) title = `Player: ${player.client.username}`;
      else if(player.botStrategy) title = `Bot: ${player.id} - ${player.botStrategy.strategy}`;
      ctx.fillStyle = 'black';
      ctx.font = '14px Arial';
      ctx.textAlign = 'center';
      ctx.fillText(title, x + rectangleWidth / 2, y + rectangleHeight / 2);

      //drawing bankroll and current bet
      ctx.fillText(player.currentBet, x + rectangleWidth / 2, y + 20);
      ctx.fillText(player.bankroll, x + rectangleWidth / 2, y + rectangleHeight - 10);

      


      //FOLD label
      // did fold?
      let round;
      if(table.currentRound) round = table.currentRound;
      else if(table.playedRounds && table.playedRounds.length > 0) round = table.playedRounds[table.playedRounds.length - 1];
      else return;

      let didFold = true;
      for(let j = 0; j < round.playersLeft.length; j++){
        if(round.playersLeft[j].id == player.id){
          didFold = false;
          break;
        }
      }
      //if folded, then write
      if(didFold){
        ctx.fillStyle = 'red';
        ctx.fillText("FOLD", x + rectangleWidth / 2, y + rectangleHeight - 25);
      }
      
      //is currently moving?
      let isMoving = table.currentPlayerPosition && round.players[table.currentPlayerPosition].id == player.id;
      if(isMoving){
        ctx.fillStyle = 'green';
        ctx.fillText("Moves", x + rectangleWidth / 2, y + rectangleHeight - 40);
      }
      

      // did the round finished and new did not started?
      // draw cards of the player
      if(!table.currentRound && !didFold || player.client && player.client.username == username){

        const cardWidth = 30;
        const cardHeight = 40;
  
        //draw the place for cards 
        const wholeWith = 20 * player.hand.length;
  
        let xC = x + (rectangleWidth  - wholeWith) / 2;
        let yC = y + rectangleHeight;
    
        //draw the cards
        for(let i = 0; i < player.hand.length; i++){
          let [rank, suit] = player.hand[i].split("_");
          let rankSymbol = rankToSymbol(+rank);
          let suitColor = suitToColor(+suit);
  
          ctx.fillStyle = '#f0f0f0'; 
          ctx.fillRect(xC + i * cardWidth, yC, cardWidth, cardHeight);
          ctx.strokeStyle = 'brown'; 
          ctx.strokeRect(xC + i * cardWidth, yC, cardWidth, cardHeight);
  
          ctx.fillStyle = suitColor;
          ctx.font = '14px Arial';
          ctx.textAlign = 'center';

          ctx.fillText(rankSymbol, xC + i * cardWidth + cardWidth / 2, yC + cardHeight / 2);
        }
      }
    }

    function drawAllPlayers(canvas, ctx){


      const horizontalRadius = canvas.width / 3;
      const verticalRadius = canvas.height / 3;

      const rectangleWidth = 100;
      const rectangleHeight = 120;
      const angleIncrement = (Math.PI * 2) / table.category.maxPlayers;

      for(let i = 0; i < table.category.maxPlayers; i++){
        if(!table.players[i]) continue;

        const angle = i * angleIncrement;
        const x = (canvas.width / 2) + Math.cos(angle) * horizontalRadius - rectangleWidth / 2;
        const y = (canvas.height / 2) + Math.sin(angle) * verticalRadius - rectangleHeight / 2; 

        drawPlayerRectangle(canvas, ctx, table.players[i], x, y, rectangleWidth, rectangleHeight);
      }
    }

    function rankToSymbol(rank){
      if(rank < 11) return rank;
      
      switch(rank){
        case 11:
          return "J";
        case 12:
          return "Q";
        case 13:
          return "K";
        case 14:
          return "A";
      }
    }

    function suitToColor(suit){
      switch(suit){
        case 0:
          return "#DC3545";
        case 1:
          return "#FFC107";
        case 2:
          return "#28A745";
        case 3:
          return "#007BFF";
      }
    }

    function drawTableCards(canvas, ctx){
      
      let round;
      if(table.currentRound) round = table.currentRound;
      else if(table.playedRounds && table.playedRounds.length > 0) round = table.playedRounds[table.playedRounds.length - 1];
      else return;

      const cardWidth = 40;
      const cardHeight = 80;

      //draw the bank:
      let x = canvas.width / 2;
      let y = canvas.width / 2;


      ctx.fillStyle = 'black';
      ctx.fillText(round.bank, x - 50 / 2, y - 20 - cardHeight  + 20 / 2 );

      //draw the place for cards 
      const wholeWith = 40 * round.tableCards.length;

      x = (canvas.width - wholeWith) / 2;
      y = (canvas.height - cardHeight) / 2;
  
      //draw the cards
      for(let i = 0; i < round.tableCards.length; i++){
        let [rank, suit] = round.tableCards[i].split("_");
        let rankSymbol = rankToSymbol(rank);
        let suitColor = suitToColor(suit);

        ctx.fillStyle = '#f0f0f0'; 
        ctx.fillRect(x + i * cardWidth, y, cardWidth, cardHeight);
        ctx.strokeStyle = 'brown'; 
        ctx.strokeRect(x + i * cardWidth, y, cardWidth, cardHeight);

        ctx.fillStyle = suitColor;
        ctx.font = '14px Arial';
        ctx.textAlign = 'center';
        ctx.fillText(rankSymbol, x + i * cardWidth + cardWidth / 2, y + cardHeight / 2);
      }
    }

    useEffect(() => {

      const canvas = canvasRef.current;
      const ctx = canvas.getContext('2d');

      ctx.clearRect(0,0,canvas.width, canvas.height);
      
      drawTable(canvas, ctx);
      drawAllPlayers(canvas, ctx);
      drawTableCards(canvas, ctx);

    }, [table]);
    
    
    return (
        <div>
            <canvas ref={canvasRef} width={size * 2} height={size}/>
        </div>
    );
}