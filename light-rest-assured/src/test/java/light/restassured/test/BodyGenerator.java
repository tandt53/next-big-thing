package light.restassured.test;
import light.restassured.rest.RestBody;
import light.restassured.exceptions.JsonElementNotFoundException;
import vinid.common.utils.Log;

public class BodyGenerator {
    private static Log log= new Log(BodyGenerator.class);

    public static void main(String[] args) throws JsonElementNotFoundException {
        RestBody body = new RestBody();

        // 1, 2, 3
        body.addArrayValue("10.137.91.51:7201", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7301", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7401", "WS.OP.OTH.INTERGRATION");

        // 4
        body.addArrayValue("10.137.91.51:7501", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7501", "WS.WAL.QR.INTERGRATION");

        // 5 
        body.addArrayValue("10.137.91.51:7601", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7601", "WS.WAL.QR.INTERGRATION");

        // 6, 7, 8
        body.addArrayValue("10.137.91.51:7211", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7311", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7411", "WS.OP.OTH.INTERGRATION");

        // 9
        body.addArrayValue("10.137.91.51:7511", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7511", "WS.WAL.QR.INTERGRATION");

        // 10
        body.addArrayValue("10.137.91.51:7611", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7611", "WS.WAL.QR.INTERGRATION");

        // 11, 12, 13
        body.addArrayValue("10.137.91.51:7221", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7321", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7421", "WS.OP.OTH.INTERGRATION");

        // 14
        body.addArrayValue("10.137.91.51:7521", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7521", "WS.WAL.QR.INTERGRATION");

        // 15
        body.addArrayValue("10.137.91.51:7621", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7621", "WS.WAL.QR.INTERGRATION");

        // 16
        body.addArrayValue("10.137.91.51:7231", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7231", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7231", "WS.OP.PAY.ROLL.INTERGRATION");

        // 17
        body.addArrayValue("10.137.91.51:7331", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7331", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7331", "WS.OP.PAY.ROLL.INTERGRATION");

        // 18
        body.addArrayValue("10.137.91.51:7431", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7431", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7431", "WS.OP.PAY.ROLL.INTERGRATION");

        // 19
        body.addArrayValue("10.137.91.51:7531", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7531", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7531", "WS.OP.PAY.ROLL.INTERGRATION");

        // 20
        body.addArrayValue("10.137.91.51:7631", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7631", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7631", "WS.OP.PAY.ROLL.INTERGRATION");


        // 21, 22, 23
        body.addArrayValue("10.137.91.52:7205", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7305", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7405", "WS.OP.OTH.INTERGRATION");

        // 24
        body.addArrayValue("10.137.91.51:7505", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7505", "WS.WAL.QR.INTERGRATION");

        // 5
        body.addArrayValue("10.137.91.51:7605", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7605", "WS.WAL.QR.INTERGRATION");

        // 26, 27, 28
        body.addArrayValue("10.137.91.51:7215", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7315", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7415", "WS.OP.OTH.INTERGRATION");

        // 29
        body.addArrayValue("10.137.91.51:7515", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7515", "WS.WAL.QR.INTERGRATION");

        // 30
        body.addArrayValue("10.137.91.51:7615", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7615", "WS.WAL.QR.INTERGRATION");

        // 31, 32, 33
        body.addArrayValue("10.137.91.51:7225", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7325", "WS.OP.OTH.INTERGRATION");
        body.addArrayValue("10.137.91.51:7425", "WS.OP.OTH.INTERGRATION");

        // 34
        body.addArrayValue("10.137.91.51:7525", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7525", "WS.WAL.QR.INTERGRATION");

        // 15
        body.addArrayValue("10.137.91.51:7625", "WS.MB.BNK.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.NAPAS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.MSB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.BIDV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.VCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.VT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.TCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.VPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.TPB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.SHB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.VIB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.SCB.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.OP.CUS.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.OP.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.WAL.CU.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.WAL.AC.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.WAL.FT.INTERGRATION");
        body.addArrayValue("10.137.91.51:7625", "WS.WAL.QR.INTERGRATION");

        // 36
        body.addArrayValue("10.137.91.51:7235", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7235", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7235", "WS.OP.PAY.ROLL.INTERGRATION");

        // 37
        body.addArrayValue("10.137.91.51:7335", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7335", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7335", "WS.OP.PAY.ROLL.INTERGRATION");

        // 38
        body.addArrayValue("10.137.91.51:7435", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7435", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7435", "WS.OP.PAY.ROLL.INTERGRATION");

        // 39
        body.addArrayValue("10.137.91.51:7535", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7535", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7535", "WS.OP.PAY.ROLL.INTERGRATION");

        // 40
        body.addArrayValue("10.137.91.51:7635", "WS.WAL.OTHER.INTERGRATION");
        body.addArrayValue("10.137.91.51:7635", "WS.WAL.RED.ENV.INTERGRATION");
        body.addArrayValue("10.137.91.51:7635", "WS.OP.PAY.ROLL.INTERGRATION");

        log.info("Started %s", body.prettyPrint());
    }
}
