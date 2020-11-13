package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemGenerator {
    public static void Problem1() {

        Place canSendIndicator = new Place("canSendIndicator", 1);
        Place incomingA = new Place("incoming-A", 1);
        Place createdA = new Place("generated-A", 0);
        Place requestedA = new Place("requested-A", 0);
        Place acceptedA = new Place("admited-A", 0);
        Place sentA = new Place("sent-A", 0);
        Place gotB = new Place("got-B", 0);
        Place allGotB = new Place("all in B", 0);
        Place incomingB = new Place("incoming-B", 1);
        Place createdB = new Place("generated-B", 0);
        Place requestedB = new Place("requested-B", 0);
        Place acceptedB = new Place("admited-B", 0);
        Place sentB = new Place("sent-B", 0);
        Place gotA = new Place("got-A", 0);
        Place allGotA = new Place("all in A", 0);

        Transition genA = new Transition("create messege A");
        Transition queryAB = new Transition("query AB");
        Transition replyBA = new Transition("replying BA");
        Transition sendAB = new Transition("sending AB");
        Transition getInB = new Transition("getting in B");
        Transition informGetInB = new Transition("successfully getting in B");
        Transition genB = new Transition("create messegee B");
        Transition queryBA = new Transition("query BA");
        Transition replyAB = new Transition("replying AB");
        Transition sendBA = new Transition("sending BA");
        Transition getInA = new Transition("getting in A");
        Transition informGetInA = new Transition("successfully getting in A");

        Arc indicatorReplyBA = new Arc("indicatorReplyBA", canSendIndicator, replyBA, 1);
        Arc incomingAGenA = new Arc("incomingAGenA", incomingA, genA, 1);
        Arc genAIncomingA = new Arc("genAIncomingA", incomingA, 1);
        Arc genAGeneratedA = new Arc("genAGeneratedA", createdA, 1);
        Arc generatedAQueryAB = new Arc("generatedAQueryAB", createdA, queryAB, 1);
        Arc queryABRequestedA = new Arc("queryABRequestedA", requestedA, 1);
        Arc requestedAReplyBA = new Arc("requestedAReplyBA", requestedA, replyBA, 1);
        Arc replyBAAdmitedA = new Arc("replyBAAdmitedA", acceptedA, 1);
        Arc admitedASendAB = new Arc("admitedASendAB", acceptedA, sendAB, 1);
        Arc sendABSentA = new Arc("sendABSentA", sentA, 1);
        Arc sentAGetInB = new Arc("sentAGetInB", sentA, getInB, 1);
        Arc getinBGotB = new Arc("getinBGotBB", gotB, 1);
        Arc gotBInfornationgB = new Arc("gotBInfornationgB", gotB, informGetInB, 1);
        Arc infGotInBIndicator = new Arc("infGotInBIndicator", canSendIndicator, 1);
        Arc infGotInBAllGotB = new Arc("infGotInBAllGotB", allGotB, 1);
        Arc indicatorReplyAB = new Arc("indicatorReplyAB", canSendIndicator, replyAB, 1);
        Arc incomingBGenB = new Arc("incomingBGenB", incomingB, genB, 1);
        Arc genBIncomingB = new Arc("genBIncomingB", incomingB, 1);
        Arc genBGeneratedB = new Arc("genBGeneratedB", createdB, 1);
        Arc generatedBQueryBA = new Arc("generatedBQueryBA", createdB, queryBA, 1);
        Arc queryBARequestedB = new Arc("queryBARequestedB", requestedB, 1);
        Arc requestedBReplyAB = new Arc("requestedBReplyAB", requestedB, replyAB, 1);
        Arc replyABAdmitedB = new Arc("replyABAdmitedB", acceptedB, 1);
        Arc admitedBSendBA = new Arc("admitedBSendBA", acceptedB, sendBA, 1);
        Arc sendBASentB = new Arc("sendBASentB", sentB, 1);
        Arc sentBGetInA = new Arc("sentBGetInA", sentB, getInA, 1);
        Arc getinAGotA = new Arc("getinAGotA", gotA, 1);
        Arc gotAInformationGotInA = new Arc("gotAInformationGotInA", gotA, informGetInA, 1);
        Arc infGotInAIndicator = new Arc("infGotInAIndicator", canSendIndicator, 1);
        Arc infGotInAAllGotA = new Arc("infGotInAAllGotA", allGotA, 1);

        genA.arcsInComming.add(incomingAGenA);
        genA.arcsOutComming.add(genAGeneratedA);
        genA.arcsOutComming.add(genAIncomingA);
        queryAB.arcsInComming.add(generatedAQueryAB);
        queryAB.arcsOutComming.add(queryABRequestedA);
        replyBA.arcsInComming.add(requestedAReplyBA);
        replyBA.arcsInComming.add(indicatorReplyBA);
        replyBA.arcsOutComming.add(replyBAAdmitedA);
        sendAB.arcsInComming.add(admitedASendAB);
        sendAB.arcsOutComming.add(sendABSentA);
        getInB.arcsInComming.add(sentAGetInB);
        getInB.arcsOutComming.add(getinBGotB);
        informGetInB.arcsInComming.add(gotBInfornationgB);
        informGetInB.arcsOutComming.add(infGotInBAllGotB);
        informGetInB.arcsOutComming.add(infGotInBIndicator);
        genB.arcsInComming.add(incomingBGenB);
        genB.arcsOutComming.add(genBGeneratedB);
        genB.arcsOutComming.add(genBIncomingB);
        queryBA.arcsInComming.add(generatedBQueryBA);
        queryBA.arcsOutComming.add(queryBARequestedB);
        replyAB.arcsInComming.add(requestedBReplyAB);
        replyAB.arcsInComming.add(indicatorReplyAB);
        replyAB.arcsOutComming.add(replyABAdmitedB);
        sendBA.arcsInComming.add(admitedBSendBA);
        sendBA.arcsOutComming.add(sendBASentB);
        getInA.arcsInComming.add(sentBGetInA);
        getInA.arcsOutComming.add(getinAGotA);
        informGetInA.arcsInComming.add(gotAInformationGotInA);
        informGetInA.arcsOutComming.add(infGotInAAllGotA);
        informGetInA.arcsOutComming.add(infGotInAIndicator);

        List<Place> places = new ArrayList<>(Arrays.asList(incomingA, createdA, requestedA, acceptedA, sentA, gotB, allGotB, canSendIndicator,
                incomingB, createdB, requestedB, acceptedB, sentB, gotA, allGotA));
        List<Transition> transitions = new ArrayList<>(Arrays.asList(genA, queryAB, replyBA, sendAB, getInB, informGetInB,
                genB, queryBA, replyAB, sendBA, getInA, informGetInA));
        Model model = new Model(places, transitions);
        model.simulate(100);
        System.out.println();
        System.out.println("B amount: " + allGotB.markersCount);
        System.out.println("A amount: " + allGotA.markersCount);
    }

    public static void Prodlem2(int maxBuffer) {
        Transition processor = new Transition("Processor");
        Transition consumer = new Transition("Consumer");

        Place incoming = new Place("Incoming", 1);
        Place buffer = new Place("Buffer", 0);
        Place stopProcessor = new Place("Free in buffer", maxBuffer);
        Place consumed = new Place("Consumed", 0);

        Arc incomingProcessor = new Arc("incoming Processor", incoming, processor, 1);
        Arc processorBuffer = new Arc("put", buffer, 1);
        Arc processorIncoming = new Arc("processorIncoming", incoming, 1);
        Arc bufferConsumer = new Arc("take", buffer, consumer, 1);
        Arc consumerStoprule = new Arc("consumer Stop ", stopProcessor, 1);
        Arc stopruleProcessor = new Arc("stop rule Processor", stopProcessor, processor, 1);
        Arc consumerConsumed = new Arc("consumer Consumed count", consumed, 1);

        processor.arcsInComming.add(incomingProcessor);
        processor.arcsInComming.add(stopruleProcessor);
        processor.arcsOutComming.add(processorBuffer);
        processor.arcsOutComming.add(processorIncoming);
        consumer.arcsInComming.add(bufferConsumer);
        consumer.arcsOutComming.add(consumerStoprule);
        consumer.arcsOutComming.add(consumerConsumed);

        List<Place> places = new ArrayList<>(Arrays.asList(incoming, buffer, stopProcessor, consumed));
        List<Transition> transitions = new ArrayList<>(Arrays.asList(processor, consumer));

        Model model = new Model(places, transitions);
        model.simulate(100);
        System.out.println();
        System.out.println("Avg markers in buffer: " + buffer.markersAvg);
    }

    public static void Problem3(int resourceAmount) {
        Transition type1Create = new Transition("Create1");
        Transition type1Process = new Transition("Process1");
        Transition type2Create = new Transition("Create2");
        Transition type2Process = new Transition("Process2");
        Transition type3Create = new Transition("Create3");
        Transition type3Process = new Transition("Process3");

        Place resources = new Place("Resources", resourceAmount);
        Place incoming1 = new Place("Incoming 1", 1);
        Place incoming2 = new Place("Incoming 2", 1);
        Place incoming3 = new Place("Incoming 3", 1);
        Place created1 = new Place("Created  1", 0);
        Place created2 = new Place("Created  2", 0);
        Place created3 = new Place("Created  3", 0);
        Place processed1 = new Place("Processed  1", 0);
        Place processed2 = new Place("Processed  2", 0);
        Place processed3 = new Place("Processed  3", 0);

        Arc incoming1Create1 = new Arc("incoming1 - Create1", incoming1, type1Create, 1);
        Arc create1Incoming1 = new Arc("create1 - Incoming1", incoming1, 1);
        Arc create1Created1 = new Arc("create1 - Created1", created1, 1);
        Arc created1Process1 = new Arc("created1 - Process1", created1, type1Process, 1);
        Arc resourcesProcess1 = new Arc("resources - Process1", resources, type1Process, resourceAmount);
        Arc process1Resources = new Arc("process1 - Resources", resources, resourceAmount);
        Arc process1Processed1 = new Arc("process1 - Processed1", processed1, 1);

        Arc incoming2Create2 = new Arc("incoming2 - Create2", incoming2, type2Create, 1);
        Arc create2Incoming2 = new Arc("create2 - Incoming2", incoming2, 1);
        Arc create2Created2 = new Arc("create2 - Created2", created2, 1);
        Arc created2Process2 = new Arc("created2 - Process2", created2, type2Process, 1);
        Arc resourcesProcess2 = new Arc("resources - Process2", resources, type2Process, resourceAmount / 3);
        Arc process2Resources = new Arc("process2 - Resources", resources, resourceAmount / 3);
        Arc process2Processed2 = new Arc("process2 - Processed2", processed2, 1);

        Arc incoming3Create3 = new Arc("incoming3 - Create3", incoming3, type3Create, 1);
        Arc create3Incoming3 = new Arc(" create3 - Incoming3 ", incoming3, 1);
        Arc create3Created3 = new Arc("create3 - Created3", created3, 1);
        Arc created3Process3 = new Arc("created3 - Process3", created3, type3Process, 1);
        Arc resourcesProcess3 = new Arc("resources - Process3 ", resources, type3Process, resourceAmount / 2);
        Arc process3Resources = new Arc("process3  - Resources", resources, resourceAmount / 2);
        Arc process3Processed3 = new Arc("process3 - Processed3", processed3, 1);

        type1Create.arcsInComming.add(incoming1Create1);
        type1Create.arcsOutComming.add(create1Incoming1);
        type1Create.arcsOutComming.add(create1Created1);
        type1Process.arcsInComming.add(created1Process1);
        type1Process.arcsInComming.add(resourcesProcess1);
        type1Process.arcsOutComming.add(process1Processed1);
        type1Process.arcsOutComming.add(process1Resources);
        type2Create.arcsInComming.add(incoming2Create2);
        type2Create.arcsOutComming.add(create2Incoming2);
        type2Create.arcsOutComming.add(create2Created2);
        type2Process.arcsInComming.add(created2Process2);
        type2Process.arcsInComming.add(resourcesProcess2);
        type2Process.arcsOutComming.add(process2Processed2);
        type2Process.arcsOutComming.add(process2Resources);
        type3Create.arcsInComming.add(incoming3Create3);
        type3Create.arcsOutComming.add(create3Incoming3);
        type3Create.arcsOutComming.add(create3Created3);
        type3Process.arcsInComming.add(created3Process3);
        type3Process.arcsInComming.add(resourcesProcess3);
        type3Process.arcsOutComming.add(process3Processed3);
        type3Process.arcsOutComming.add(process3Resources);

        List<Place> places = new ArrayList<>(Arrays.asList(resources, incoming1, incoming2, incoming3,
                created1, created2, created3,
                processed1, processed2, processed3));
        List<Transition> transitions = new ArrayList<>(Arrays.asList(type1Create, type1Process, type2Create, type2Process, type3Create, type3Process
        ));
        Model model = new Model(places, transitions);
        model.simulate(100);
        System.out.println();
        int allProcessed = processed1.markersCount + processed2.markersCount + processed3.markersCount;

        System.out.println("Processed amount: " + allProcessed);
        System.out.println(" " + "Type" + " Processed " + "part of all");
        System.out.println(" " + "1 Type " + processed1.markersCount + " " + ((processed1.markersCount / allProcessed))* 100);
        System.out.println(" " + "2 Type " + processed2.markersCount + " " + ((processed2.markersCount / allProcessed)));
        System.out.println(" " + "3 Type " + processed3.markersCount + " " + ((processed3.markersCount / allProcessed)));
    }
}
