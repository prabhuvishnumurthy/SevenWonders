package ca.polymtl.inf8405.sevenwonders;

import android.util.Log;
import ca.polymtl.inf8405.sevenwonders.api.*;
import org.apache.thrift.TException;

import static ca.polymtl.inf8405.sevenwonders.api.Card.*;
import static ca.polymtl.inf8405.sevenwonders.api.Civilisation.*;

import java.util.*;

public class ReceiverStub extends Api {


    public static ReceiverStub getInstance(){
        return instance;
    }

    public void addObserver( Api observer ){
        observers.add( observer );
    }

    public void simulate_c_begin(){

        Set<Map<Resource,List<NeighborReference>>> emptyTrade =
                new HashSet<Map<Resource,List<NeighborReference>>>();

        Map<CardCategory,List<Card>> emptyTableau = new HashMap<CardCategory,List<Card>>();
        List<Integer> battleMarkersEmpty = new LinkedList<Integer>();

        /* Player 1 */
        Player player1 = new Player(
            emptyTableau,
            HALICARNASSUS_B,
            battleMarkersEmpty,
            3,
            0,
            0,
            false
        );

        Map<Card,Set<Map<Resource,List<NeighborReference>>>> playables =
            new HashMap<Card,Set<Map<Resource,List<NeighborReference>>>>();

        List<Card> unplayables = new LinkedList<Card>();

        playables.put(PRESS, emptyTrade);
        playables.put(STOCKADE, emptyTrade);
        playables.put(GUARD_TOWER, emptyTrade);
        playables.put(LUMBER_YARD, emptyTrade);
        playables.put(EXCAVATION, emptyTrade);
        playables.put(EAST_TRADING_POST, emptyTrade);
        playables.put(BARRACKS, emptyTrade);

        /* Player 2 */
        Player player2 = new Player(
                emptyTableau,
                RHODES_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );

        /* Player 3 */
        Player player3 = new Player(
                emptyTableau,
                ALEXANDRIA_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );

        /* Player 4 */
        Player player4 = new Player(
                emptyTableau,
                ALEXANDRIA_B,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );
        
        GameState state = new GameState(
            new Hand( playables, unplayables ),
            new ArrayList<Player>(Arrays.asList(player1, player2, player3))
        );

        try {
            c_begin( state );
        } catch ( TException e ) {
            Log.wtf("Stub","Stub");
        }
    }

    @Override public void c_begin(GameState state) throws TException {
        for( Api observer: observers ) {
            observer.c_begin( state );
        }
    }

    public void simulate_c_sendState(){
        Set<Map<Resource,List<NeighborReference>>> emptyTrade =
                new HashSet<Map<Resource,List<NeighborReference>>>();

        Map<CardCategory,List<Card>> emptyTableau = new HashMap<CardCategory,List<Card>>();
        List<Integer> battleMarkersEmpty = new LinkedList<Integer>();

        /* Player 1 */
        Player player1 = new Player(
                emptyTableau,
                HALICARNASSUS_B,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );

        Map<Card,Set<Map<Resource,List<NeighborReference>>>> playables =
                new HashMap<Card,Set<Map<Resource,List<NeighborReference>>>>();

        List<Card> unplayables = new LinkedList<Card>();

        Set<Map<Resource,List<NeighborReference>>> trades = 
        		new HashSet<Map<Resource,List<NeighborReference>>>();
        
        List<NeighborReference> twoNeighbors = new ArrayList<NeighborReference>(2);
        twoNeighbors.add(NeighborReference.LEFT);
        twoNeighbors.add(NeighborReference.RIGHT);
        List<NeighborReference> oneNeighbor = new ArrayList<NeighborReference>(1);
        oneNeighbor.add(NeighborReference.LEFT);
        
        Map<Resource,List<NeighborReference>> trade1 = new HashMap<Resource,List<NeighborReference>>();
        trade1.put(Resource.CLAY, twoNeighbors);
        trade1.put(Resource.ORE, oneNeighbor);
        Map<Resource,List<NeighborReference>> trade2 = new HashMap<Resource,List<NeighborReference>>();
        trade2.put(Resource.GLASS, oneNeighbor);
        trade2.put(Resource.STONE, oneNeighbor);
        trades.add(trade1);
        trades.add(trade2);
        
        playables.put(STOCKADE, trades);
        playables.put(GUARD_TOWER, trades);
        playables.put(LUMBER_YARD, trades);
        playables.put(EXCAVATION, trades);
        playables.put(EAST_TRADING_POST, emptyTrade);
        playables.put(BARRACKS, emptyTrade);

        /* Player 2 */
        Player player2 = new Player(
                emptyTableau,
                RHODES_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );

        /* Player 3 */
        Player player3 = new Player(
                emptyTableau,
                ALEXANDRIA_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );

        /* Player 4 */
        Player player4 = new Player(
                emptyTableau,
                OLYMPIA_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );
        
        /* Player 5 */
        Player player5 = new Player(
                emptyTableau,
                EPHESUS_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );
        
        /* Player 6 */
        Player player6 = new Player(
                emptyTableau,
                GIZAH_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );
        
        /* Player 7 */
        Player player7 = new Player(
                emptyTableau,
                BABYLON_A,
                battleMarkersEmpty,
                3,
                0,
                0,
                false
        );
        
        GameState state = new GameState(
                new Hand( playables, unplayables ),
                new ArrayList<Player>(Arrays.asList(player1, player2, player3))
        );

        try {
            c_sendState( state );
        } catch ( TException e ) {
            Log.wtf("Stub","Stub");
        }
    }

    @Override public void c_sendState(GameState state) throws TException {
        for( Api observer: observers ) {
            observer.c_sendState( state );
        }
    }

    private List<Api> observers = new LinkedList<Api>();
    private static ReceiverStub instance = new ReceiverStub();
    private ReceiverStub() {}
}
