package com.bigdata.platform.worker;


import com.bigdata.platform.protocol.ProtocolProto;

/**
 * Worker is use to resolve Protocol, and get Request Message from Protocol.
 * According to Request Message, handle request then generic Response,
 * finally, put the Response into Protocol.
 */
interface Worker {
    /**
     * resolve Protocol, then get Request. finally, obtain request message.
     * */
    Worker resolveReqProtocol();

    /**
     * handle client request.
     * */
    Worker execute();

    /**
     * obtain Protocol.
     * @return ProtocolProto.protocol
     * */
    ProtocolProto.protocol returnProtocol();
}
