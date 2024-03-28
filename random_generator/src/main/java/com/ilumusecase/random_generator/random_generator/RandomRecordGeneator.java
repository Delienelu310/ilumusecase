package com.ilumusecase.random_generator.random_generator;

import java.util.Random;
import java.util.Stack;

import com.ilumusecase.game.ActionType;
import com.ilumusecase.game.Card;

public class RandomRecordGeneator {
    
    private Random random = new Random();


    public ActionRecord generateRandomActionRecord(){

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setRoundId(random.nextLong(1000l));
        actionRecord.setPosition(random.nextInt(6));
        actionRecord.setActionNumber(random.nextInt(42));

        actionRecord.setBigBlind(100);
        actionRecord.setBankroll(actionRecord.getBigBlind() * random.nextInt(10, 100));
        actionRecord.setBank((int)(actionRecord.getBigBlind() * 
            Math.pow(random.nextDouble(1.05, 1.3), actionRecord.getActionNumber())
        ));
        Integer betSize = random.nextBoolean() ? (int)(actionRecord.getBank() * random.nextDouble(0.5, 1.5)) : 0;
        actionRecord.setMaxPreviousBetSize(betSize);

        Integer playerBetSize = random.nextDouble() > 0.8 ? betSize : random.nextDouble() > 0.5 ? 0 : (int)(betSize * random.nextDouble(0.2, 0.5));
        actionRecord.setPlayerPreviousBetSize(playerBetSize);
        
        //action type and action size
        switch(random.nextInt(0, 3)){
            case 0:
                actionRecord.setActionType(ActionType.FOLD);
                actionRecord.setActionSize(0);
                break;
            case 1:
                if(actionRecord.getMaxPreviousBetSize() > 0) actionRecord.setActionType(ActionType.CALL);
                else actionRecord.setActionType(ActionType.CHECK);

                actionRecord.setActionSize(0);
            case 2:
                Integer size = 0;
                if(actionRecord.getMaxPreviousBetSize() > 0) {
                    actionRecord.setActionType(ActionType.RAISE);
                    size = (int)(actionRecord.getMaxPreviousBetSize() * random.nextDouble(1, 3));
                }else{
                    actionRecord.setActionType(ActionType.BET);
                    size = (int)(actionRecord.getBank() * random.nextDouble(0.3, 1.2));
                }
                if(size > actionRecord.getBankroll()) size = actionRecord.getBankroll();

                actionRecord.setActionSize(size);
                break;
        }


        //cards
        Integer stageNumber = (int)(actionRecord.getActionNumber() / random.nextInt(1, 7) / random.nextDouble(0.5, 2));
        if(stageNumber > 3) stageNumber = 3;

        Stack<Card> cards = new Stack<>();
        Integer cardsNumber = 2;
        if(!stageNumber.equals(0)) cardsNumber += 2 + stageNumber;
        

        for(int i = 0; i < cardsNumber; i++){
            boolean isUsed = true;
            Card card = null;

            while(isUsed){
                isUsed = false;
                card = new Card(random.nextInt(2, 15), random.nextInt(0, 4));
                for(Card c : cards){
                    if(c.getRank().equals(card.getRank()) && c.getSuit().equals(card.getSuit())){
                        isUsed = true;
                        break;
                    }
                }
            }
            cards.push(card);
        }

        Card[] hand = new Card[2];
        hand[0] = cards.pop();
        hand[1] = cards.pop();
        actionRecord.setHand(hand);
        
        
        Card[] tableCards = new Card[5];
        for(int i = 0; i < cardsNumber - 2; i++){
            tableCards[i] = cards.pop();
        }
        actionRecord.setTableCards(tableCards);

        return actionRecord;
    
    }

}
