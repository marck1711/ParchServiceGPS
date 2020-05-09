package com.example.parchservicesms;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

import com.example.parchservicesms.Model.Solicitud;
import com.example.parchservicesms.Servicio.GetSolicitud;
import com.example.parchservicesms.Servicio.Ubicacion;
import com.example.parchservicesms.Servicio.UpdateSolicitud;


public class GPSSend {
    private Context ctx;

    public GPSSend(Context context) {
        this.ctx = context;
    }

    public Boolean Obtener() {
        Solicitud[] solicitudes = ObtenerSolicitudes();
        if (solicitudes != null) {
            for (int i = 0; i < solicitudes.length; i++) {
                ActualizarEstadoUbicacion(solicitudes[i]);
            }
        }
        return true;
    }

    private Solicitud[] ObtenerSolicitudes() {
        try {
            GetSolicitud solicitud = new GetSolicitud("ABIERTA", getPhoneNumber());
            solicitud.execute();
            Thread.sleep(1000);
            return solicitud.solicitudes;
        } catch (InterruptedException ex) {
            return null;
        }
    }

    private void ActualizarEstadoUbicacion(Solicitud solicitud) {
        Ubicacion ubicacion = new Ubicacion(ctx);
        ubicacion.localicacion();
        solicitud.setX(String.valueOf(ubicacion.latitud));
        solicitud.setY(String.valueOf(ubicacion.longitud));
        solicitud.setEstado("FINALIZADO");
        UpdateSolicitud updateSolicitud = new UpdateSolicitud(solicitud);
        updateSolicitud.execute();
    }

    private String getPhoneNumber() {

      //  TelephonyManager tMgr = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        //if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
      //      return tMgr.getLine1Number();
      //  }
        return "959542602";
    }
}
