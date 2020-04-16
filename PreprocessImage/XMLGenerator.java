import java.io.File;
import java.lang.reflect.InvocationTargetException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLGenerator {

    String path = "";
    String path_original = "";
    int type = 1;
    float angle = 0;
    int num_dials = 0;
    int orien = 1;
    float ulx = 0;
    float uly = 0;
    float blx = 0;
    float bly = 0;
    float urx = 0;
    float ury = 0;
    float brx = 0;
    float bry = 0;
    int left = 0;
    int right = 0;
    int bottom_dial_type = 1;


    XMLGenerator(String _path, String _path_original, int _type, float _angle, int _num_dials,
                 int _orien, float _ulx, float _uly, float bly, float _urx, float _ury, float _br,
                 float _bry, int _left, int _right, int _bottom_dial_type) {
        path = _path;
        path_original = _path_original;
        type = _type;
        angle = _angle;
        num_dials = _num_dials;
        orien = _orien;
        ulx = _ulx;
    }

    public void generateXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("properties");
            doc.appendChild(rootElement);

            Element image_path = doc.createElement("image_path");
            image_path.appendChild(doc.createTextNode(path));
            rootElement.appendChild(image_path);

            Element image_unedited = doc.createElement("image_unedited");
            image_unedited.appendChild(doc.createTextNode(path_original));
            rootElement.appendChild(image_unedited);

            Element meter_type = doc.createElement("meter_type");
            meter_type.appendChild(doc.createTextNode(Integer.toString(type)));
            rootElement.appendChild(meter_type);

            Element base_angle = doc.createElement("base_angle");
            base_angle.appendChild(doc.createTextNode(Float.toString(angle)));
            rootElement.appendChild(base_angle);

            Element number_of_dial = doc.createElement("number_of_dial");
            number_of_dial.appendChild(doc.createTextNode(Integer.toString(num_dials)));
            rootElement.appendChild(number_of_dial);

            Element orientation = doc.createElement("orientation");
            orientation.appendChild(doc.createTextNode(Integer.toString(orien)));
            rootElement.appendChild(orientation);

            Element coordinates = doc.createElement("coordinates");
            rootElement.appendChild(coordinates);

            Element upper_left_x = doc.createElement("upper_left_x");
            upper_left_x.appendChild(doc.createTextNode(Float.toString(ulx)));
            coordinates.appendChild(upper_left_x);

            Element upper_left_y = doc.createElement("upper_left_y");
            upper_left_y.appendChild(doc.createTextNode(Float.toString(uly)));
            coordinates.appendChild(upper_left_y);

            Element bottom_left_x = doc.createElement("bottom_left_x");
            bottom_left_x.appendChild(doc.createTextNode(Float.toString(blx)));
            coordinates.appendChild(bottom_left_x);

            Element bottom_left_y = doc.createElement("bottom_left_y");
            bottom_left_y.appendChild(doc.createTextNode(Float.toString(bly)));
            coordinates.appendChild(bottom_left_y);

            Element upper_right_x = doc.createElement("upper_right_x");
            upper_right_x.appendChild(doc.createTextNode(Float.toString(urx)));
            coordinates.appendChild(upper_right_x);

            Element upper_right_y = doc.createElement("upper_right_y");
            upper_right_y.appendChild(doc.createTextNode(Float.toString(ury)));
            coordinates.appendChild(upper_right_y);

            Element bottom_right_x = doc.createElement("bottom_right_x");
            bottom_right_x.appendChild(doc.createTextNode(Float.toString(brx)));
            coordinates.appendChild(bottom_right_x);

            Element bottom_right_y = doc.createElement("bottom_right_y");
            bottom_right_y.appendChild(doc.createTextNode(Float.toString(bry)));
            coordinates.appendChild(bottom_right_y);

            Element boundary_reading = doc.createElement("boundary_reading");
            rootElement.appendChild(boundary_reading);

            Element left_reading = doc.createElement("left_reading");
            left_reading.appendChild(doc.createTextNode(Integer.toString(left)));
            boundary_reading.appendChild(left_reading);

            Element right_reading = doc.createElement("right_reading");
            right_reading.appendChild(doc.createTextNode(Integer.toString(right)));
            boundary_reading.appendChild(right_reading);

            Element log_or_linear = doc.createElement("log_or_linear");
            log_or_linear.appendChild(doc.createTextNode(Integer.toString(bottom_dial_type)));
            rootElement.appendChild(log_or_linear);


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            String fileName = "XML/meter_property.xml";
            StreamResult result = new StreamResult(new File(fileName));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void main(String argv[]) {

    }
}