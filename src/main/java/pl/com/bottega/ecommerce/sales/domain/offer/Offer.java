package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.ArrayList;
import java.util.List;

public class Offer {

    private List<OfferItem> items = new ArrayList<OfferItem>();

//	private List<OfferItem> availabeItems = new ArrayList<OfferItem>();
//
//	private List<OfferItem> unavailableItems = new ArrayList<OfferItem>();

    public Offer(List<OfferItem> items) {
        this.items = items;
    }


//	public Offer(List<OfferItem> items, List<OfferItem> availabeItems, List<OfferItem> unavailableItems) {
//		this.items = items;
//		this.availabeItems = availabeItems;
//		this.unavailableItems = unavailableItems;
//	}

//	public List<OfferItem> getAvailabeItems() {
//		return availabeItems;
//	}
//
//	public List<OfferItem> getUnavailableItems() {
//		return unavailableItems;
//	}

    public List<OfferItem> getItems() {
        return items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        // Problem with refactoring to one list, because hash code is calculated only from available offerItems
        // Context and use case of hashCode in application needed?
//		result = prime * result
//				+ ((availabeItems == null) ? 0 : availabeItems.hashCode());
        result = prime * result
                + ((items == null) ? 0 : items.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Offer other = (Offer) obj;

        // Same problem as in hashCode method
//		if (availabeItems == null) {
//			if (other.availabeItems != null)
//				return false;
//		} else if (!availabeItems.equals(other.availabeItems))
//			return false;

        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        return true;
    }

    /**
     * @param seenOffer
     * @param delta     acceptable difference in percent
     * @return
     */
    public boolean sameAs(Offer seenOffer, double delta) {
        if (!(items.size() == seenOffer.items.size()))
            return false;

        for (OfferItem item : items) {
            OfferItem sameItem = seenOffer.findItem(item.getProduct());
            if (sameItem == null)
                return false;
            if (!sameItem.sameAs(item, delta))
                return false;
        }

        return true;
    }

    private OfferItem findItem(Product product) {
        for (OfferItem item : items) {
            if (item.getProduct().equals(product))
                return item;
        }
        return null;
    }


}
