package com.siegester.skyrimalchemy.Tips;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siegester.skyrimalchemy.R;

/**
 * Created by Siegester on 3/11/2017.
 */

public class TipsFragment extends Fragment {

    private TextView _txvEffDiscovery = null;

    public static TipsFragment newInstance() {
        TipsFragment fragment = new TipsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public TipsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStat) {

        View baseView = inflater.inflate(R.layout.tips_layout, container, false);
        _txvEffDiscovery = (TextView) baseView.findViewById(R.id.txv_EffDiscovery);
        _txvEffDiscovery.setText(
                "Easy - Vendor Ingredients Only\n" +
                "1. Creep Cluster + Dwarven Oil + Taproot \n" +
                "2. Elves Ear + Fire Salts + White Cap \n" +
                "3. Cyrodilic Spadetail + Jazbay Grapes + Salt Pile \n" +
                "4. Dragon's Tongue + Fly Amanita + Scaly Pholiota \n" +
                "5. Slaughterfish Egg + Slaughterfish Scales + Thistle Branch \n" +
                "6. Abecean Longfin + Bleeding Crown + Ice Wraith Teeth \n" +
                "7. Bee + Giant Lichen + Mora Tapinella \n" +
                "8. Bleeding Crown + Tundra Cotton + Void Salts \n" +
                "9. Blisterwort + Blue Mountain Flower + Hagraven Feathers \n" +
                "10. Blue Butterfly Wing + Bone Meal + Snowberries \n" +
                "11. Daedra Heart + Giant's Toe + Wheat \n" +
                "12. Falmer Ear + Fly Amanita + Troll Fat \n" +
                "13. Juniper Berries + Moon Sugar + Vampire Dust \n" +
                "14. Canis Root + Falmer Ear + Spider Egg \n" +
                "15. Chaurus Eggs + Lavender + Nirnroot \n" +
                "16. Blisterwort + Hagraven Claw + Spriggan Sap \n" +
                "17. Butterfly Wing + Hagraven Claw + Wheat \n" +
                "18. Giant's Toe + River Betty + Salt Pile \n" +
                "19. Bone Meal + Deathbell + Giant Lichen \n" +
                "20. Canis Root + Imp Stool + Mora Tapinella \n" +
                "21. Daedra Heart + Rock Warbler Egg + Scaly Pholiota \n" +
                "22. Hagraven Claw + Lavender + White Cap \n" +
                "23. Histcarp + Nordic Barnacle + Red Mountain Flower \n" +
                "24. Ice Wraith Teeth + Luna Moth Wing + Vampire Dust \n" +
                "25. Cyrodilic Spadetail + Daedra Heart + Nirnroot" +
                "26. Dwarven Oil + Garlic + Slaughterfish Egg \n" +
                "27. Canis Root + Juniper Berries + Rock Warbler Egg \n" +
                "28. Beehive Husk + Ectoplasm \n" +
                "29. Falmer Ear + Namira's Rot\n" +
                "\n" +
                "Medium - Some Hunting Required\n" +
                "30. Charred Skeever Hide + Eye of Sabre Cat + Mudcrab Chitin \n" +
                "31. Glowing Mushroom + Pine Thrush Egg + Sabre Cat Tooth \n" +
                "32. Blue Dartwing + Imp Stool + Swamp Fungal Pod \n" +
                "33. Frost Mirriam + Orange Dartwing + Purple Mountain Flower \n" +
                "34. Honeycomb + Silverside Perch + Skeever Tail \n" +
                "35. Beehive Husk + Canis Root + Hawk Feathers \n" +
                "36. Beehive Husk + Grass Pod + Red Mountain Flower \n" +
                "37. Chaurus Eggs + Deathbell + Large Antlers \n" +
                "38. Blue Dartwing + Namira's Rot + Nordic Barnacle \n" +
                "39. Blue Mountain Flower + Glow Dust + Glowing Mushroom \n" +
                "40. Abecean Longfin + Butterfly Wing + Small Antlers \n" +
                "41. Chicken's Egg + Histcarp + Large Antlers \n" +
                "42. Beehive Husk + Garlic + Luna Moth Wing \n" +
                "43. Eye of Sabre Cat + Moon Sugar + Silverside Perch \n" +
                "44. Honeycomb + Pearl + Thistle Branch \n" +
                "45. Hawk Beak + Mudcrab Chitin + Snowberries \n" +
                "46. Orange Dartwing + Slaughterfish Scales + Small Pearl\n" +
                "\n" +
                "Hard - Possible Effort Required\n" +
                "47. Bear Claws + Small Pearl + Hanging Moss\n" +
                "48. Frost Salts + Hawk Beak + Pearl\n" +
                "49. Briar Heart + Ectoplasm + Human Flesh\n" +
                "50. Chicken's Egg + Nightshade + Wisp Wrappings\n" +
                "51. Creep Cluster + Torchbug Thorax + Wisp Wrappings\n" +
                "52. Abecean Longfin + Bee + Powdered Mammoth Tusk\n" +
                "53. Cyrodilic Spadetail + Frost Salts + Powdered Mammoth Tusk\n" +
                "54. Hagraven Feathers + Human Heart + Void Salts\n" +
                "55. Human Flesh + Purple Mountain Flower + Torchbug Thorax\n" +
                "56. Human Heart + River Betty + Spriggan Sap ");
        return baseView;
    }

}
