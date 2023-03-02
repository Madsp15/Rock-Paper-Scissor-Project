package rps.bll.player;

import rps.bll.game.Move;

public class MarkovChain {
    private int[][] transitionMatrix;

    public MarkovChain() {
        // Initialize the transition matrix with zeros
        this.transitionMatrix = new int[3][3];
    }

    public void updateTransitionMatrix(Move previousMove, Move currentMove) {
        int previousIndex = moveIndex(previousMove);
        int currentIndex = moveIndex(currentMove);
        transitionMatrix[previousIndex][currentIndex]++;
    }

    public Move predictNextMove(Move previousMove) {
        int previousIndex = moveIndex(previousMove);
        int[] row = transitionMatrix[previousIndex];
        int maxIndex = 0;
        for (int i = 1; i < row.length; i++) {
            if (row[i] > row[maxIndex]) {
                maxIndex = i;
            }
        }

        return moveFromIndex(maxIndex);
    }

    private int moveIndex(Move move) {
        switch (move) {
            case Rock:
                return 0;
            case Paper:
                return 1;
            case Scissor:
                return 2;
            default:
                return -1;
        }
    }

    private Move moveFromIndex(int index) {
        switch (index) {
            case 0:
                return Move.Paper;
            case 1:
                return Move.Scissor;
            case 2:
                return Move.Rock;
            default:
                return null;
        }
    }
}

