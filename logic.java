private void redrw(GoogleMap googleMap) {
googleMap.clear();
mMap = googleMap;
m_handler = new Handler();

try {
File file = new File("sdcard/Download", "mydir");
if (!file.exists()) {
file.mkdir();
}
File file1 = new File(file, "data.txt");
FileWriter writer = new FileWriter(file1);
File gpxfile = new File(p, f);
BufferedReader br = new BufferedReader(new FileReader(gpxfile)); String line;
String[] sep = new String[40];
line = br.readLine();
writer.append(line + "\n");
line = br.readLine();
writer.append(line + "\n");
line = br.readLine();
writer.append(line + "\n");
while ((line = br.readLine()) != null) {
writer.append(line + "\n");
sep = line.split("\t");
double d = Double.parseDouble(sep[10]);
double d1 = Double.parseDouble(sep[11]);
double r = Double.parseDouble(sep[17]);
double e = Double.parseDouble(sep[18]);
double x = Double.parseDouble(sep[14]) / 6377000;
double y = Double.parseDouble(sep[13]) / (6377000 * (Math.cos(r / 57.3) + x * Math.sin(x 57.3)));
x = x * 57.3;
y = y * 57.3;
j++;
LatLng ltlg = new LatLng(x + r, y + e);
pts.add(ltlg);
LatLng latLng = new LatLng(d, d1);
points.add(latLng);
}
writer.flush();
writer.close();
} catch (IOException e) {
e.printStackTrace();
}
try {
File gpxfile = new File(p, f);
final BufferedReader br1 = new BufferedReader(new FileReader(gpxfile));
line1 = br1.readLine();
line1 = br1.readLine();
line1 = br1.readLine();
m_handlerTask = new Runnable() {
@Override
public void run() {
if ((t < points.size() - 1) && k == 1) {
Button b7 = (Button) findViewById(R.id.pause);
b7.setOnClickListener(new View.OnClickListener() {
public void onClick(View arg0) {
Button b2 = (Button) findViewById(R.id.pause);
String s = (String) b2.getText();
if (s.equalsIgnoreCase("pause")) {
b2.setText("play");
k = 0;
} 
else if (s.equalsIgnoreCase("play")) { b2.setText("pause");
k = 1;
}
}
}
try {
line1 = br1.readLine();
} catch (Exception e) {
}
DecimalFormat df = new DecimalFormat("#.##");
sep1 = line1.split("\t");
LatLng src = pts.get(t);
LatLng dest = pts.get(t + 1);
if (src.latitude != dest.latitude || src.longitude != dest.longitude) {
double res = Math.toDegrees(Math.atan2(dest.latitude - src.latitude, src.longitude -dest.longitude)); res = (res < 0) ? (360 + res) : res;
if (r != 0) {
m.remove();
}
Polyline line = mMap.addPolyline(new PolylineOptions()
.add(new LatLng(src.latitude, src.longitude),
new LatLng(dest.latitude, dest.longitude))
.width(5).color(Color.RED).geodesic(false));
mMap.moveCamera(CameraUpdateFactory.newLatLng(pts.get(t + 1)));
MarkerOptions opt = new MarkerOptions()
.position(dest)
.icon(BitmapDescriptorFactory.fromResource(R.drawable.arr2) .rotation((float) res-90);
m	= mMap.addMarker(opt); r++;
}
t++;
TextView t1 = (TextView) findViewById(R.id.gpslat);
t1.setText("Lat: " + Double.toString(src.latitude).substring(0, 9)); TextView t2 = (TextView) findViewById(R.id.gpslon);
t2.setText("Long: " + Double.toString(src.longitude).substring(0, 9));
TextView t3 = (TextView) findViewById(R.id.frame);
Double x = Double.parseDouble(sep1[1]);
t3.setText("Frame: " + df.format(x));
} else {
m_handler.removeCallbacks(m_handlerTask);
}
m_handler.postDelayed(m_handlerTask, 100);
}
};
m_handlerTask.run();
} 
catch (IOException e) { 
e.printStackTrace();
}
}


